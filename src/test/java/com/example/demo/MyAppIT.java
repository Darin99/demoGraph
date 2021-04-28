package com.example.demo;

import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.ODatabaseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.OrientDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
public class MyAppIT {

    @Container
    private static final OrientDBContainer CONTAINER = new OrientDBContainer(DockerImageName.parse("orientdb:3.1.9").asCompatibleSubstituteFor("orientdb"));

    private static ODatabasePool oDatabasePool;

    @TestConfiguration
    public static class TestBeans {
        @Bean
        public ODatabasePool pool() {
            CONTAINER.getOrientDB().createIfNotExists(CONTAINER.getDatabaseName(), ODatabaseType.PLOCAL);
            oDatabasePool = new ODatabasePool(CONTAINER.getOrientDB(), CONTAINER.getDatabaseName(), "admin", "admin");
            try (ODatabaseSession session = oDatabasePool.acquire()) {
                createClasses(session);
                createProperties(session);
                createIndexes(session);
            }
            return oDatabasePool;
        }

        private void createClasses(ODatabaseSession session) {
            session.command("CREATE CLASS Employee EXTENDS V");
            session.command("CREATE CLASS Manager EXTENDS V");
            session.command("CREATE CLASS HrRepresentative EXTENDS V");
            session.command("CREATE CLASS Company EXTENDS V");
            session.command("CREATE CLASS Team EXTENDS V");

            session.command("CREATE CLASS worksIn EXTENDS E");
            session.command("CREATE CLASS isPartOf EXTENDS E");
            session.command("CREATE CLASS isIn EXTENDS E");
            session.command("CREATE CLASS isRepresentedBy EXTENDS E");
            session.command("CREATE CLASS isManagedBy EXTENDS E");
        }

        private void createProperties(ODatabaseSession session) {
            session.command("CREATE PROPERTY Employee.name STRING");
            session.command("CREATE PROPERTY Manager.name STRING");
            session.command("CREATE PROPERTY HrRepresentative.name STRING");
            session.command("CREATE PROPERTY Company.name STRING");
            session.command("CREATE PROPERTY Team.name STRING");

            session.command("CREATE PROPERTY worksIn.in LINK");
            session.command("CREATE PROPERTY worksIn.out LINK");
            session.command("CREATE PROPERTY isPartOf.in LINK");
            session.command("CREATE PROPERTY isPartOf.out LINK");
            session.command("CREATE PROPERTY isIn.in LINK");
            session.command("CREATE PROPERTY isIn.out LINK");
            session.command("CREATE PROPERTY isRepresentedBy.in LINK");
            session.command("CREATE PROPERTY isRepresentedBy.out LINK");
            session.command("CREATE PROPERTY isManagedBy.in LINK");
            session.command("CREATE PROPERTY isManagedBy.out LINK");
        }

        private void createIndexes(ODatabaseSession session) {
            session.command("CREATE INDEX EmployeeUniqueName ON Employee (name) UNIQUE");
            session.command("CREATE INDEX ManagerUniqueName ON Manager (name) UNIQUE");
            session.command("CREATE INDEX HrRepresentativeUniqueName ON HrRepresentative (name) UNIQUE");
            session.command("CREATE INDEX CompanyUniqueName ON Company (name) UNIQUE");
            session.command("CREATE INDEX TeamUniqueName ON Team (name) UNIQUE");

            session.command("CREATE INDEX worksIn_composite ON worksIn (in, out) UNIQUE");
            session.command("CREATE INDEX isPartOf_composite ON isPartOf (in, out) UNIQUE");
            session.command("CREATE INDEX isIn_composite ON isIn (in, out) UNIQUE");
            session.command("CREATE INDEX isRepresentedBy_composite ON isRepresentedBy (in, out) UNIQUE");
            session.command("CREATE INDEX isManagedBy_composite ON isManagedBy (in, out) UNIQUE");
        }
    }

    @Test
    public void givenCorrectDataParWhenCheckingPropertiesThenCorrect() {
        try (ODatabaseSession session = oDatabasePool.acquire()) {
            long count = session.command("SELECT COUNT(*) FROM V WHERE name IS DEFINED").next().getProperty("COUNT(*)");
            Assertions.assertEquals(13, count);
        }
    }

    @Test
    public void givenCorrectDataWhenParsingFilesThenCorrect() {
        try (ODatabaseSession session = oDatabasePool.acquire()) {
            long count = session.command("SELECT COUNT(*) FROM V").next().getProperty("COUNT(*)");
            Assertions.assertEquals(13, count);
        }
    }

    @Test
    public void givenCorrectDataWhenCreatingEdgeThenCorrect() {
        try (ODatabaseSession session = oDatabasePool.acquire()) {
            long count = session.command("SELECT COUNT(*) FROM E").next().getProperty("COUNT(*)");
            Assertions.assertEquals(23, count);
        }
    }
}