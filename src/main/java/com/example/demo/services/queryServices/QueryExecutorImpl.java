package com.example.demo.services.queryServices;

import com.example.demo.services.queryServices.interfaces.QueryExecutor;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.exception.OConcurrentModificationException;
import com.orientechnologies.orient.core.storage.ORecordDuplicatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class QueryExecutorImpl implements QueryExecutor {

    private final ODatabasePool pool;
    private static final int MAX_RETRIES = 7;
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutorImpl.class);

    @Autowired
    public QueryExecutorImpl(ODatabasePool pool) {
        this.pool = pool;
    }

    @Override
    public void executeQuery(String query) {
        AtomicInteger retryCounter = new AtomicInteger();

        while (retryCounter.get() < MAX_RETRIES) {
            try (ODatabaseSession session = pool.acquire()) {
                session.command(query);
                break;
            } catch (OConcurrentModificationException e) {
                retryCounter.incrementAndGet();
                if (retryCounter.get() >= MAX_RETRIES) {
                    break;
                }
            } catch (ORecordDuplicatedException e) {
                LOGGER.error("Cannot create edge, because it already exist!");
                break;
            }
        }
    }
}