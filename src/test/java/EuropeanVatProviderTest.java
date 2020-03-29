import Product.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EuropeanVatProviderTest {

    private EuropeanVatProvider vatProvider;

    @BeforeEach
    void setUp() {
        vatProvider = new EuropeanVatProvider();
    }

    @Test
    void shouldReturn5PercentageForBooksInPoland() {
        //when
        BigDecimal polishBooksVat = vatProvider.getVatFor("Poland", Type.BOOK);

        //then
        assertThat(polishBooksVat).isEqualTo(new BigDecimal("0.05"));
    }

    @Test
    void shouldReturn8PercentageForFoodsInPoland() {
        //when
        BigDecimal polishFoodsVat = vatProvider.getVatFor("Poland", Type.FOOD);

        //then
        assertThat(polishFoodsVat).isEqualTo(new BigDecimal("0.08"));
    }

    @Test
    void shouldReturn4PercentageForBooksInGermany() {
        //when
        BigDecimal germanyBooksVat = vatProvider.getVatFor("Germany", Type.BOOK);

        //then
        assertThat(germanyBooksVat).isEqualTo(new BigDecimal("0.04"));
    }

    @Test
    void shouldReturn21PercentageForStandardsInGermany() {
        //when
        BigDecimal germanyStandardVat = vatProvider.getVatFor("Germany", Type.GAMES);

        //then
        assertThat(germanyStandardVat).isEqualTo(new BigDecimal("0.21"));
    }

    @Test
    void shouldReturn8PercentageForBooksInDenmark() {
        //when
        BigDecimal denmarkBooksVat = vatProvider.getVatFor("Denmark", Type.BOOK);

        //then
        assertThat(denmarkBooksVat).isEqualTo(new BigDecimal("0.08"));
    }

    @Test
    void shouldReturn8PercentageForStandardsInDenmark() {
        //when
        BigDecimal denmarkStandardVat = vatProvider.getVatFor("Denmark", Type.SHOES);

        //then
        assertThat(denmarkStandardVat).isEqualTo(new BigDecimal("0.08"));
    }

    @Test
    void shouldReturnCountryNotSupportedExceptionForRussia() {
        //when
        Exception exception = assertThrows(CountryNotSupportedException.class, () ->
            vatProvider.getVatFor("Russia", Type.FOOD));

        //then
        assertThat(exception.getMessage()).isEqualTo("This country is not supported: Russia");
    }
}