package it.unisa.c03.myPersonalTrainer;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class ParametersService {

    /**
     *
     * @param peso massimo consentito 150.99
     * @param massaMagra
     * @param massagGrassa
     * @return
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    public Parameters acceptParams(String peso, String massaMagra, String massagGrassa) throws NumberFormatException,IllegalArgumentException{
        if (!peso.matches("([0-9]){2,3}\\.?[0-9]{0,2}?$")) throw new NumberFormatException("Formato Peso non valido");
        if (!massagGrassa.matches("[0-9]+")) throw new NumberFormatException("Formato massagGrassa non valido");
        if (!massaMagra.matches("[0-9]+")) throw new NumberFormatException("Formato massaMagra non valido");

        float pesoF = Float.parseFloat(peso);
        float massaMagraF=Float.parseFloat(massaMagra);
        float massaGrassaF=Float.parseFloat(massagGrassa);
        System.out.println("ecco i parse" + pesoF + " "+ massaGrassaF + " "+massaMagraF);



        if (peso.length() < 2 || peso.length() > 5 || Float.valueOf(peso) < 40 || Float.valueOf(peso) > 150) throw new IllegalArgumentException("Lunghezza peso non valida");
        if (massagGrassa.length() < 2 || massagGrassa.length() > 3 || Float.valueOf(massagGrassa)< 40 || Float.valueOf(massagGrassa) > 150) throw new IllegalArgumentException("Lunghezza massagGrassa non valida");
        if (massaMagra.length() < 2 || massaMagra.length() > 3 || Float.valueOf(massaMagra) < 40 || Float.valueOf(massaMagra) > 150) throw new IllegalArgumentException("Lunghezza massaMagra non valida");

        Parameters pa = new Parameters(pesoF,massaMagraF,massaGrassaF,"mail",new Date());
        return pa;


    }

    /**
     *
     * @param peso
     * @param massaMagra
     * @param massagGrassa
     * @throws IOException
     * @throws NumberFormatException
     * peso - massaMagra - massagGrassa compresi tra 40 e 150
     */
    public void acceptablePLenght(String peso, String massaMagra, String massagGrassa) throws IOException {

        if (peso.length() < 2 || peso.length() > 3 || Float.valueOf(peso) < 40 || Float.valueOf(peso) > 150) throw new IllegalArgumentException("Lunghezza peso non valida");
        if (massagGrassa.length() < 2 || massagGrassa.length() > 3 || Float.valueOf(massagGrassa)< 40 || Float.valueOf(massagGrassa) > 150) throw new IllegalArgumentException("Lunghezza massagGrassa non valida");
        if (massaMagra.length() < 2 || massaMagra.length() > 3 || Float.valueOf(massaMagra) < 40 || Float.valueOf(massaMagra) > 150) throw new IllegalArgumentException("Lunghezza massaMagra non valida");
    }

    public boolean controllaPeso(String peso) throws NumberFormatException {

        if (!peso.matches("[0-9]+")) throw new NumberFormatException("Formato Peso non valido");
        return true;

    }

    public boolean controllaMassagGrassa(String massagGrassa) throws NumberFormatException {

        if (!massagGrassa.matches("[0-9]+")) throw new NumberFormatException("Formato massagGrassa non valido");
        return true;

    }

    public boolean controllaMM(String mm) throws NumberFormatException {

        if (!mm.matches("[0-9]+")) throw new NumberFormatException("Formato massaMagra non valido");
        return true;

    }

}
