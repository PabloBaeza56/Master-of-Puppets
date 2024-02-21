package scrapper;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ObtenerExperiencia extends MinadoDatos{

    private ArrayList<Integer> elementosCasoSimple;
    private ArrayList<Integer> elementosCasoCompuesto;
    private int seccionDeseada;


    public ObtenerExperiencia(WebDriver driver) {
        super(driver);
        this.elementosCasoSimple = new ArrayList<>();
        this.elementosCasoCompuesto = new ArrayList<>();    
    }

    private void determinarTipoSecciones() {
        this.seccionDeseada = super.buscarIndiceSeccion("Experiencia");

        int i = 1;
        while (true) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));

                try {
                    WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + 1 + "]/div/div[2]/div/a"));
                    WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));

                    this.elementosCasoCompuesto.add(i);
                } catch (NoSuchElementException e) {
                    this.elementosCasoSimple.add(i);
                }

                i++;
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    public void seccionExperienciaCasoSimple() {

        for (int i : this.elementosCasoSimple) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

                //------------NO MOVER
                try {
                    WebElement puestoElement = elementoBase.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                    String[] titulo = puestoElement.getText().split("\\n");
                    System.out.println("Título: " + titulo[0]);
                } catch (NoSuchElementException e) {
                }

                //------------NO MOVER
                try {
                    WebElement empresaElemento = elementoBase.findElement(By.xpath(".//span[@class='t-14 t-normal']"));
                    String[] puesto = empresaElemento.getText().split("\\n");
                    System.out.println("Empresa: " + puesto[0]);
                } catch (NoSuchElementException e) {
                }

                //------------NO MOVER
                try {
                    WebElement fechaElemento = elementoBase.findElement(By.xpath(".//span[contains(@class, 't-14 t-normal t-black--light')][1]"));
                    String[] fecha = fechaElemento.getText().split("\\n");
                    System.out.println("Fecha: " + fecha[0]);
                } catch (NoSuchElementException e) {
                }

                //------------NO MOVER
                try {
                    WebElement fechaElemento = elementoBase.findElement(By.xpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"));
                    String[] fecha = fechaElemento.getText().split("\\n");
                    System.out.println("Ubicacion: " + fecha[0]);
                } catch (NoSuchElementException e) {
                }

                try {
                    WebElement descripcionElemento = elementoBase.findElement(By.xpath(".//div[contains(@class, 'pv-shared-text-with-see-more')]//span"));
                    String[] descripcion = descripcionElemento.getText().split("\\n");
                    System.out.println("Descripción: " + descripcion[0]);
                } catch (NoSuchElementException e) {
                }

                //String codigoHTML = elementoBase.getAttribute("outerHTML");
                //System.out.println(codigoHTML);
                System.out.println("---------------------");
            } catch (NoSuchElementException e) {
            }

        }
    }

    public void seccionExperienciaCasoCompuesto() {

        for (int i : this.elementosCasoCompuesto) {
            try {
                WebElement elementoBase = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div/div/main/section[" + this.seccionDeseada + "]/div[3]/ul/li[" + i + "]/div/div[2]"));///div/div/div/div/div/div/span[1]

                String Titulo = "";
                try {
                    WebElement puestoElement = elementoBase.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                    String[] titulo = puestoElement.getText().split("\\n");
                    //System.out.println("Empresa: " + titulo[0]);
                    Titulo = titulo[0];
                } catch (NoSuchElementException e) {}

                int contador = 1;
                while (true) {
                    try {
                        WebElement fechaElemento = elementoBase.findElement(By.xpath("./div[2]/ul/li[" + contador + "]/div/div[2]/div/a")); // /a
                        System.out.println("Empresa: " + Titulo);
                        try {
                            WebElement puestoElement = fechaElemento.findElement(By.xpath(".//div[@class='display-flex flex-wrap align-items-center full-height']"));
                            String[] titulo = puestoElement.getText().split("\\n");
                            System.out.println("Puesto: " + titulo[0]);
                        } catch (NoSuchElementException e) {
                        }

                        try {
                            WebElement empresaElemento = fechaElemento.findElement(By.xpath(".//span[@class='t-14 t-normal']"));
                            String[] puesto = empresaElemento.getText().split("\\n");
                            System.out.println("Tipo de contrato: " + puesto[0]);
                        } catch (NoSuchElementException e) {
                        }

                        try {
                            WebElement fechaElxxemento = fechaElemento.findElement(By.xpath(".//span[contains(@class, 't-14 t-normal t-black--light')][1]"));
                            String[] fechax = fechaElxxemento.getText().split("\\n");
                            System.out.println("Duracion : " + fechax[0]);
                        } catch (NoSuchElementException e) {
                        }

                        try {
                            WebElement fechaElxxemento = fechaElemento.findElement(By.xpath(".//span[contains(@class, 't-14 t-normal t-black--light')][2]"));
                            String[] fechay = fechaElxxemento.getText().split("\\n");
                            System.out.println("Ubicacion : " + fechay[0]);
                        } catch (NoSuchElementException e) {
                        }

                        //REPARAR-------------------------------------------------
                        try {
                            WebElement descripcionElemento = fechaElemento.findElement(By.xpath(".//div[contains(@class, 'pv-shared-text-with-see-more full-width t-14 t-normal t-black display-flex align-items-center')]"));
                            String[] descripcion = descripcionElemento.getText().split("\\n");
                            System.out.println("Descripción: " + descripcion[0]);
                        } catch (NoSuchElementException e) {
                        }

                        contador++;
                        System.out.println("---------------------");
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }

                //String codigoHTML = elementoBase.getAttribute("outerHTML");
                //System.out.println(codigoHTML);
                System.out.println("XXXXXXXXXX");
                i++;
            } catch (NoSuchElementException e) {break;}
        }
    }

}
