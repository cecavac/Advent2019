package com.celecavac.advent2019;

import java.util.ArrayList;

public class Rafinery {
    private long result = 0;
    private long result2 = 0;

    private Element ore = new Element("1 ORE");
    private Element fuel = new Element("1 FUEL");
    private Formula []formulas;

    public Rafinery(String input) {
        String []formulaData = input.split("\\n");
        formulas = new Formula[formulaData.length];
        for (int i = 0; i < formulas.length; i++) {
            formulas[i] = new Formula(formulaData[i]);
        }
    }

    public static void aggregate(ArrayList<Element> elements) {
        ArrayList<Element> result = new ArrayList<Element>();

        main_loop: for (int i = 0; i < elements.size(); i++) {
            /* Skip already aggregated */
            for (Element resultElement: result)
                if (resultElement.equals(elements.get(i)))
                    continue main_loop;

            long count = elements.get(i).getQuantity();
            for (int j = i + 1; j < elements.size(); j++) {
                if (elements.get(i).equals(elements.get(j)))
                    count += elements.get(j).getQuantity();
            }

            result.add(new Element(count + " " + elements.get(i).getParticle()));
        }

        elements.clear();
        elements.addAll(result);
    }


    public void reverseProduction(long target) {
        boolean changed = false;
        ArrayList<Element> materials = new ArrayList<Element>();
        ArrayList<Element> reserves = new ArrayList<Element>();
        fuel.setQuantity(target);
        materials.add(fuel);

        do {
            changed = false;
            ArrayList<Element> toBeRemoved = new ArrayList<Element>();
            ArrayList<Element> toBeAdded = new ArrayList<Element>();

            for (Element material: materials) {
                for (Formula formula: formulas) {
                    if (material.equals(formula.getOutput())) {
                        toBeRemoved.add(material);
                        ArrayList<Element> newMaterials = formula.reverseCalculation(material, reserves);
                        for (Element newMaterial: newMaterials)
                            if (newMaterial.getQuantity() > 0)
                                toBeAdded.add(newMaterial);
                        changed = true;
                    }
                }
            }

            for (Element material: toBeRemoved)
                materials.remove(material);
            aggregate(toBeAdded);
            for (Element material: toBeAdded)
                materials.add(material);

            aggregate(materials);
        } while(changed);

        result = materials.get(0).getQuantity();
    }

    public void findTrilionOutput() {
        long trillion = 1000000000000L;
        double estimation = ((double) trillion) / result;
        estimation *= 1.4;
        long input1 = (long) estimation;
        long input2 = 0;

        do {
            result = 0;

            /* reduce by 0.005% */
            input1 = (input1 * 99995) / 100000;
            input2 = input1;
            while (result < trillion) {
                reverseProduction(input2++);
            }
        } while (input1 == --input2);

        result2 = --input2;
    }

    public long getResult() {
        return result;
    }

    public long getResult2() {
        return result2;
    }
}
