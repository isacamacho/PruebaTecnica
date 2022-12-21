package prueba;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;



public class Test_Google {

	WebDriver driver;
	
@Before

public void setUpChrome() {
	
	System.setProperty("webdriver.chrome.driver", "..\\pruebaTecnica\\drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	
}

@Test

public void test() throws IOException {
	
	driver.navigate().to("http://www.google.es");
	
	//Aviso google
	WebElement avisoRechazar = driver.findElement(By.xpath("//*[@id=\"W0wltc\"]") );
	avisoRechazar.click();
	
	//localizamos buscador e introducimos lo que queremos buscar
	WebElement buscador = driver.findElement(By.xpath("//input[@class='gLFyf']"));
	buscador.click();
	buscador.sendKeys("automatización");
	buscador.submit();
	
	//Localizamos la url wikipedia de la busquedad realizada y hacemos click sobre ella
	driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
	WebElement link = driver.findElement(By.xpath("//a[@href = 'https://es.wikipedia.org/wiki/Automatizaci%C3%B3n']"));
	System.out.println("Entramos en el link " + link.getText());
	link.click();
	
	//Se hace busqueda del texto y lo validamos que exista en la página
	WebElement texto = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p[33]"));
	String textoBuscado = "En 1771 Richard Arkwright inventó la primera hilandería totalmente automatizada accionada por energía hidráulica, conocida en la época como water frame.27 Un molino harinero automático fue desarrollado por Oliver Evans en 1785, convirtiéndose en el primer proceso industrial completamente automatizado.28";
	Assert.assertEquals(textoBuscado,texto.getText());
	System.out.println("El texto se ha encontardo correctamente: " + texto.getText());
	
	//Captura de pantalla
	File dest = new File("C:\\Users\\Isa Camacho\\Desktop\\PruebaTecnica\\pruebaTecnica\\capturas\\capturas.png");
	Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	ImageIO.write(s.getImage(),"png",dest);
	System.out.println("Captura de pantalla guardada.");
		
	
}

@After

public void tearDown() {
	
	driver.quit();
	System.out.println("Se cierra el test correctamente.");
	
}
	

}
