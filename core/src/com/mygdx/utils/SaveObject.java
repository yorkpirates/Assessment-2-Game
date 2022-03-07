package com.mygdx.utils;

import com.mygdx.game.Entitys.Player;
import com.mygdx.game.Entitys.Ship;
import com.mygdx.game.Managers.GameManager;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public final class SaveObject  {
    /**
     * This classes job is to hold all the data needed to reconstruct the game state
     * Its the object that will be written using serilisation to a file
     */

    //uses STAX parser
    public static void writeXMl(){

        try{
            //setUP outputs
            StringWriter stringWriter = new StringWriter();
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter =
                    xMLOutputFactory.createXMLStreamWriter(new FileOutputStream("test.xml"));
            //write the xml
            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("SAVE");

            //write all the ships
            xMLStreamWriter.writeStartElement("Ships");
            xMLStreamWriter.writeAttribute("Number", "value");
            int i =0;
            for(Ship s : GameManager.ships){
                writeShiptoXML(xMLStreamWriter,s,i);
                i = i +1;

            }
            xMLStreamWriter.writeEndElement();

            //write the game data
            Player p = GameManager.getPlayer();
            writeGamedatatoXML(xMLStreamWriter,p);

            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            //String xmlString = stringWriter.getBuffer().toString();

            stringWriter.close();
            //System.out.println(xmlString);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeShiptoXML(XMLStreamWriter xmlDoc,Ship ship,int index) throws XMLStreamException {
        xmlDoc.writeStartElement("position");

        xmlDoc.writeStartElement("X");
        xmlDoc.writeCharacters(String.valueOf(ship.getPosition().x));
        xmlDoc.writeEndElement();

        xmlDoc.writeStartElement("Y");
        xmlDoc.writeCharacters(String.valueOf(ship.getPosition().y));
        xmlDoc.writeEndElement();

        xmlDoc.writeStartElement("Index");
        xmlDoc.writeCharacters(String.valueOf(index));
        xmlDoc.writeEndElement();

        xmlDoc.writeEndElement();
    }
    private static void writeGamedatatoXML(XMLStreamWriter xmlDoc,Player player) throws XMLStreamException{
        xmlDoc.writeStartElement("GAMEDATA");

        xmlDoc.writeStartElement("Ammo");
        xmlDoc.writeCharacters(String.valueOf(player.getAmmo()));
        xmlDoc.writeEndElement();

        xmlDoc.writeStartElement("Plunder");
        xmlDoc.writeCharacters(String.valueOf(player.getPlunder()));
        xmlDoc.writeEndElement();


        xmlDoc.writeEndElement();
    }

    public static void readXML(String path){
        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(path));

            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                    switch (event.getEventType()){

                        case XMLStreamConstants.START_ELEMENT:
                            StartElement startElement = event.asStartElement();
                            String qName = startElement.getName().getLocalPart();

                            if(qName.equalsIgnoreCase("ships")){

                            }
                            else if(qName.equalsIgnoreCase("position")){
                                placeShip(eventReader);
                            }
                            break;

                        case XMLStreamConstants.CHARACTERS:
                            Characters characters = event.asCharacters();
                            System.out.println(characters.getData());
                            break;

                        case XMLStreamConstants.END_ELEMENT:
                            break;
                    }

            }



        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (XMLStreamException e){
            e.printStackTrace();
        }
    }

    private static void placeShip(XMLEventReader eventReader) throws XMLStreamException {
        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
        XMLEvent event = eventReader.nextEvent();

        Characters chars = event.asCharacters();
        float x = Float.parseFloat(chars.getData()) ;

        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        float y = Float.parseFloat(chars.getData()) ;

        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        int index = Integer.parseInt(chars.getData()) ;
        GameManager.ships.get(index).setPosition(x,y);

    }

}
