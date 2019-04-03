package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import sk.kracina1.db2.application.ConnectionTest;
import sk.kracina1.db2.application.DbContext;
import sk.kracina1.db2.application.rdg.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//class MailAvailableTest {
//
//    private User user;
//    private IsValidMail isValidMail;
//    private static final String testMail = "jankohrasko@ficinapraskoch.com";
//    private ConnectionTest connection = ConnectionTest.getInstance();
//
//    @BeforeAll
//    static void beforeAll() {
//        ConnectionTest.getInstance().connect();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        ConnectionTest.getInstance().clear();
//    }
//
//    @BeforeEach
//    void setUp() {
//        isValidMail = new IsValidMail();
//        try {
//            user = new User();
//            user.setMail(testMail);
//            user.insert();
//        } catch(SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//        try {
//            user.delete();
//        } catch(SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    void notAvailable() {
//        assertEquals(isValidMail.passes(testMail), false);
//    }
//
//
//    @org.junit.jupiter.api.Test
//    void available() {
//        assertEquals(isValidMail.passes("jankohrasko2@ficinapraskoch.com"), true);
//    }
//
//}