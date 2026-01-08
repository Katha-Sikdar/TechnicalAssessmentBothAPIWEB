package locators;

public class pageObjects {
    public static final String OneWayRadioButton = "//div[@data-testid='One Way-input']";

    public static final String DepartureDateField= "//p[text()='Departure']/parent::div";
    public static final String TomorrowDate = "//div[contains(@class, 'react-datepicker__day--today')]/following::div[1]";
    public static final String SearchBtn =" //button[@data-testid='search-flight-button']";

    public static final String SearchRslt =" //p[text()='No flights found']";

}
