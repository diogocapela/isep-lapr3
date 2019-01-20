package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    private Receipt receipt0;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;

    private Park park;

    @BeforeAll
    static void startUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @BeforeEach
    void setUp() {
        receipt0 = null;
        receipt1 = new Receipt(1, 1, 1, 20.0);
        receipt2 = new Receipt(1, 2, 2, 25.0);
        receipt3 = new Receipt(2, 3, 3, 100.0);

        park = new Park();
        park.setIdPark(1);
    }

    @Test
    void getSetIdReceipt() {
        assertNotEquals(5, receipt1.getIdReceipt());
        receipt1.setIdReceipt(5);
        assertEquals(5, receipt1.getIdReceipt().intValue());
    }

    @Test
    void getSetIdInvoice() {
        assertNotEquals(5, receipt1.getIdInvoice());
        receipt1.setIdInvoice(5);
        assertEquals(5, receipt1.getIdInvoice().intValue());
    }

    @Test
    void getSetIdUser() {
        assertNotEquals(5, receipt1.getIdUser());
        receipt1.setIdUser(5);
        assertEquals(5, receipt1.getIdUser().intValue());
    }

    @Test
    void getSetCost() {
        assertNotEquals(10.0, receipt1.getCost());
        receipt1.setCost(10.0);
        assertEquals(10.0, receipt1.getCost().doubleValue());
    }

    @Test
    void equalsTest() {
        assertEquals(receipt1, receipt1);

        assertEquals(receipt1, receipt2);

        assertNotEquals(receipt1, receipt0);

        assertNotEquals(receipt1, park);

        assertNotEquals(receipt1, receipt3);
    }

    @Test
    void hashCodeTest() {
        assertEquals(receipt1.hashCode(), receipt1.hashCode());

        assertEquals(receipt1.hashCode(), receipt2.hashCode());

        assertNotEquals(receipt1.hashCode(), park.hashCode());

        assertNotEquals(receipt1.hashCode(), receipt3.hashCode());
    }

    @Test
    void toStringTest() {
        String result1 = null;
        String result2 = null;
        assertNull(result1);
        assertNull(result2);
        assertEquals(result1, result2);

        result1 = receipt1.toString();
        result2 = receipt2.toString();
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotEquals(result1, result2);
    }
}
