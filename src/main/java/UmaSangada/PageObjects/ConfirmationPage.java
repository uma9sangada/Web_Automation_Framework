package UmaSangada.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UmaSangada.AbstractComponents.AbstractComponents;

/**
 * Hello world!
 *
 */
public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver)// by default default constructor is called
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".hero-primary")
	WebElement message;

	public String odermessage() 
	{
		return  message.getText();

	}

	

}
