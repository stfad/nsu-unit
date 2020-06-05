package Core;

public class Assert {
    public static void assertTrue(boolean cond) throws AssertionFailed {
        if (!cond) {
            throw new AssertionFailed("EXPECTED TRUE, RECEIVED FALSE");
        }
    }

    public static void assertFalse(boolean cond) throws AssertionFailed {
        if (cond) {
            throw new AssertionFailed("EXPECTED FALSE, RECEIVED TRUE");
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionFailed("EXPECTED EQUALS, RECEIVED " + expected.toString() + " , " + actual.toString());
        }
    }

    public static void assertNotEquals(Object expected, Object actual) {
        if (expected.equals(actual)) {
            throw new AssertionFailed("EXPECTED NOT EQUALS, RECEIVED " + expected.toString() + " , " + actual.toString());
        }
    }

    public static void assertNotNull(Object object) {
        if (object == null)
            throw new AssertionFailed("EXPECTED NOT NULL, RECEIVED NULL");
    }

    public static void assertNull(Object object) {
        if (object != null)
            throw new AssertionFailed("EXPECTED NULL, RECEIVED " + object.toString());
    }

    public static void assertIntegerEquals(int expected, int actual) {
        if (expected != actual)
            throw new AssertionFailed("EXPECTED EQUALS, RECEIVED " + expected + " , " + actual);
    }
}
