package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    private Invoice invoice0;
    private Invoice invoice1;
    private Invoice invoice2;
    private Invoice invoice3;

    private Park park;

    @BeforeAll
    static void startUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @BeforeEach
    void setUp() {
        invoice0 = null;
        invoice1 = new Invoice(1, 1, 5.0, 4.0, 10, "boom");
        invoice2 = new Invoice(1, 2, 6.0, 4.0, 20, "boom2");
        invoice3 = new Invoice(2, 3, 4.0, 4.0, 0, "boom3");

        park = new Park();
        park.setIdPark(1);
    }

    @Test
    void getSetIdInvoiceTest() {
        assertNotEquals(5, invoice1.getIdInvoice());
        invoice1.setIdInvoice(5);
        assertEquals(5, invoice1.getIdInvoice().intValue());
    }

    @Test
    void getSetIdRideTest() {
        assertNotEquals(5, invoice1.getIdUser());
        invoice1.setIdUser(5);
        assertEquals(5, invoice1.getIdUser().intValue());
    }

    @Test
    void getSetTotalCostTest() {
        assertNotEquals(10.0, invoice1.getTotalCost());
        invoice1.setTotalCost(10.0);
        assertEquals(10.0, invoice1.getTotalCost().doubleValue());
    }

    @Test
    void getSetMoneyPaidTest() {
        assertNotEquals(10.0, invoice1.getMoneyPaid());
        invoice1.setMoneyPaid(10.0);
        assertEquals(10.0, invoice1.getMoneyPaid().doubleValue());
    }

    @Test
    void getSetPointsUsedTest() {
        assertNotEquals(5, invoice1.getPointsUsed());
        invoice1.setPointsUsed(5);
        assertEquals(5, invoice1.getPointsUsed().intValue());
    }

    @Test
    void getSetStateTest() {
        assertNotEquals("woohoo", invoice1.getStatus());
        invoice1.setStatus("woohoo");
        assertEquals("woohoo", invoice1.getStatus());
    }

    @Test
    void equalsTest() {
        assertEquals(invoice1, invoice1);

        assertEquals(invoice1, invoice2);

        assertNotEquals(invoice1, invoice0);

        assertNotEquals(invoice1, park);

        assertNotEquals(invoice1, invoice3);
    }

    @Test
    void hashCodeTest() {
        assertEquals(invoice1.hashCode(), invoice1.hashCode());

        assertEquals(invoice1.hashCode(), invoice2.hashCode());

        assertNotEquals(invoice1.hashCode(), park.hashCode());

        assertNotEquals(invoice1.hashCode(), invoice3.hashCode());
    }

    @Test
    void toStringTest() {
        String result1 = null;
        String result2 = null;
        assertNull(result1);
        assertNull(result2);
        assertEquals(result1, result2);

        result1 = invoice1.toString();
        result2 = invoice2.toString();
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotEquals(result1, result2);
    }
}
