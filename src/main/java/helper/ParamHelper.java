package helper;

import exception.BadRequestException;
import javafx.util.Pair;

import java.util.Map;

public class ParamHelper {

    private Map<String, String[]> parameters;

    public ParamHelper(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public String getString(String name) throws BadRequestException {
        String[] parameterArray = parameters.get (name);
        if (parameterArray == null || parameterArray.length == 0) {
            throw new BadRequestException ("Cannot find parameter by name: " + name);
        }
        return parameterArray[0];
    }

    public String[] getStringArray(String name) throws BadRequestException {
        String[] parameterArray = parameters.get (name);
        if (parameterArray == null || parameterArray.length == 0) {
            throw new BadRequestException ("Cannot find parameter by name: " + name);
        }
        return parameterArray;
    }

    public Pair<String, String> geEntryString(String name) throws BadRequestException {
        return new Pair<> (name, getString (name));
    }

    public Pair<String, String[]> geEntryStringArray(String name) throws BadRequestException {
        return new Pair<> (name, getStringArray (name));
    }


    public Integer getInt(String name) throws BadRequestException {
        try {
            return Integer.parseInt (getString (name));
        } catch (NumberFormatException e) {
            throw new BadRequestException (e);
        }
    }

    public Float getFloat(String name) throws BadRequestException {
        try {
            return Float.parseFloat (getString (name));
        } catch (NumberFormatException e) {
            throw new BadRequestException (e);
        }
    }

    public Double getDouble(String name) throws BadRequestException {
        try {
            return Double.parseDouble (getString (name));
        } catch (NumberFormatException e) {
            throw new BadRequestException (e);
        }
    }


    public Pair<String, Integer> geEntryInt(String name) throws BadRequestException {
        return new Pair<> (name, getInt (name));
    }
}

