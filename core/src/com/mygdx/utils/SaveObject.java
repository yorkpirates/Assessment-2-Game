package com.mygdx.utils;

import com.badlogic.gdx.Game;
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

    /**
     * A method which saves the Game to XML using a STAX parser
     *
     * @param path The path to where the file will be saved
     */
    public static void writeXMl(String path){

        try{
            //setUP outputs
            StringWriter stringWriter = new StringWriter();
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter =
                    xMLOutputFactory.createXMLStreamWriter(new FileOutputStream(path));
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

    /**
     * Takes the  Ship and writes it to XML
     *
     * @param xmlDoc The Document which is being written to
     * @param ship  The Ship object which we want to save in XML
     * @param index The index of the ship
     * @throws XMLStreamException
     */
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

        xmlDoc.writeStartElement("Health");
        xmlDoc.writeCharacters(String.valueOf(ship.getHealth()));
        xmlDoc.writeEndElement();

        xmlDoc.writeEndElement();
    }

    /**
     * Method that writes game data to XML
     *
     * @param xmlDoc The Document which is being written to
     * @param player The player Object which contains the data we wish to write
     * @throws XMLStreamException
     */
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

    /**
     * Loads a saved game which is an XML file 
     *
     * @param path the path to the XML file that the method will load
     */
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
                            else if(qName.equalsIgnoreCase("GAMEDATA")){
                                loadGameData(eventReader);
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

    /**
     * Reads A ships data from XMl and updates the game to place it in it.
     *
     * @param eventReader The eventReader currently parsing the tree
     * @throws XMLStreamException
     */
    private static void placeShip(XMLEventReader eventReader) throws XMLStreamException {
        //skip through the xml till we find data values
        Next(eventReader);
        XMLEvent event = eventReader.nextEvent();
        Characters chars = event.asCharacters();
        float x = Float.parseFloat(chars.getData()) ;

        Next(eventReader);
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        float y = Float.parseFloat(chars.getData()) ;

        Next(eventReader);
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        int index = Integer.parseInt(chars.getData()) ;

        Next(eventReader);
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        int health = Integer.parseInt(chars.getData());


        GameManager.ships.get(index).setPosition(x,y);
        GameManager.ships.get(index).setHealth(health);
        if(health<=0){
            GameManager.ships.get(index).ShipDeath();
        }
    }

    /**
     * Iterates through the XML tree until it comes to a character
     *
     * @param eventReader The event reader currently parsing the tree
     * @throws XMLStreamException
     */
    private static void Next(XMLEventReader eventReader) throws XMLStreamException {
        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
    }

    /**
     * Reads Game data from XML
     * @param eventReader The eventReader currently parsing the tree
     * @throws XMLStreamException
     */
    private static void loadGameData(XMLEventReader eventReader) throws XMLStreamException {

        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
        XMLEvent event = eventReader.nextEvent();

        Characters chars = event.asCharacters();
        int ammo = Integer.parseInt(chars.getData());
        GameManager.getPlayer().setAmmo(ammo);

        while (! eventReader.peek().isCharacters()){
            eventReader.nextEvent();
        }
        event = eventReader.nextEvent();
        chars = event.asCharacters();
        int plunder = Integer.parseInt(chars.getData());
        //as game starts at 0 add the money from the save on.
        GameManager.getPlayer().plunder(plunder);

    }
}
