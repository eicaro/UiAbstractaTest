import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.Product;
import pages.ResultsPage;
import pages.SeleniumConfig;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class TestSearchPage {
    public SeleniumConfig config;
    private WebDriver driver;
    @Before
    public void setUp() throws Exception {
        config = new SeleniumConfig("https://www.mercadolibre.com.uy/",false);
        config.setUp();
        driver = config.getDriver();
    }

    @Test
    public void testSearchAndSaveProductList() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.searchForItem("Camisetas");
        ResultsPage searchResultsPage = new ResultsPage(driver);

        List<WebElement> linksCamisetas = searchResultsPage.getCamisetasLinks();
        List<String> precios = searchResultsPage.getPrecios(linksCamisetas.size());
        List<Product> productos = null;

        productos = searchResultsPage.createProductList(productos,linksCamisetas, precios);
        searchResultsPage.acceptCookies();
        searchResultsPage.goToNextPage();

        linksCamisetas = searchResultsPage.getCamisetasLinks();
        precios = searchResultsPage.getPrecios(linksCamisetas.size());
        productos = searchResultsPage.createProductList(productos,linksCamisetas, precios);
        searchResultsPage.goToNextPage();

        linksCamisetas = searchResultsPage.getCamisetasLinks();
        precios = searchResultsPage.getPrecios(linksCamisetas.size());
        productos = searchResultsPage.createProductList(productos,linksCamisetas, precios);

        String outputFileName = UUID.randomUUID().toString() + ".txt";
        Product.writeProductsToFile(productos, outputFileName);
    }

    @After
    public void tearDown() throws Exception {
        config.tearDown();
    }
}
