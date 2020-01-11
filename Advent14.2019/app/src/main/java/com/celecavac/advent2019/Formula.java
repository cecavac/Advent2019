package com.celecavac.advent2019;

import java.util.ArrayList;

public class Formula {
    private Element []input;
    private Element output;

    public Formula(String data) {
        String []dataArray = data.split(" => ");
        output = new Element(dataArray[1]);

        String []inputData = dataArray[0].split(", ");
        input = new Element[inputData.length];
        for (int i = 0; i < inputData.length; i++) {
            input[i] = (new Element(inputData[i]));
        }
    }

    public Element getOutput() {
        return output;
    }

    public static void reuseResources(Element element, ArrayList<Element> reserves) {
        for (Element reserve: reserves) {
            if (reserve.getQuantity() == 0)
                continue;

            if (reserve.equals(element)) {
                if (element.getQuantity() <= reserve.getQuantity()) {
                    long newReserveQuantity = reserve.getQuantity() - element.getQuantity();
                    reserve.setQuantity(newReserveQuantity);
                    element.setQuantity(0);
                } else {
                    long newReverseQuantity = element.getQuantity() - reserve.getQuantity();
                    reserve.setQuantity(0);
                    element.setQuantity(newReverseQuantity);
                }
            }
        }
    }

    public ArrayList<Element> reverseCalculation(Element element, ArrayList<Element> reserves) {
        ArrayList<Element> reverseInputs = new ArrayList<Element>();

        if (!element.equals(output))
            throw new NullPointerException();

        reuseResources(element, reserves);
        if (element.getQuantity() == 0)
            return reverseInputs;

        long toBeProduced = element.getQuantity();
        if (toBeProduced % output.getQuantity() != 0) {
            long offset = output.getQuantity() - (toBeProduced % output.getQuantity());
            toBeProduced += offset;
            /* Don't waste currently unneeded resources */
            long unneeded = toBeProduced - element.getQuantity();
            reserves.add(new Element(unneeded + " " + output.getParticle()));
        }
        long productionFactor = toBeProduced / output.getQuantity();

        for (int i = 0; i < input.length; i++) {
            long quantity = productionFactor * input[i].getQuantity();
            reverseInputs.add(new Element(quantity + " " + input[i].getParticle()));
        }

        /* Try to reuse saved resources */
        Rafinery.aggregate(reserves);
        for (Element reverseInput: reverseInputs)
            reuseResources(reverseInput, reserves);

        return  reverseInputs;
    }
}
