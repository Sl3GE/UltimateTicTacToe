package UTTT.Player.AI.Genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GeneticCode {
    private ArrayList<Double> genes;
    private int geneCount;

    public GeneticCode() {
        genes = new ArrayList<>();
    }

    public GeneticCode(int geneCount) {
        genes = new ArrayList<>(geneCount);
        this.geneCount = geneCount;
    }

    public GeneticCode(Collection<? extends Double> collection) {
        this(collection, collection.size());
    }

    public GeneticCode(Collection<? extends Double> collection, int collectionSize) {
        genes = new ArrayList<>(collection);
        geneCount = collectionSize;
    }

    public ArrayList<Double> getGenes() {
        return genes;
    }

    public int getGeneCount() {
        return geneCount;
    }

    public Double getGene(int index) {
        return genes.get(index);
    }

    public void generateNewGeneSet(int amount, double lowerBound, double upperBound) {
        genes = new ArrayList<>(amount);
        geneCount = amount;
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            double randomDouble = random.nextDouble() * (upperBound - lowerBound) + lowerBound;
            genes.set(i,randomDouble);
        }
    }

    public void mutate(int amtToMutate, double adjustmentValue) {
        Random random = new Random();
        int amountToMutate = amtToMutate > geneCount ? geneCount : amtToMutate;
        for (int i = 0; i < amountToMutate; i++) {
            int index = random.nextInt(geneCount);
            double randomAdjustmentValue = random.nextDouble() * (2 * adjustmentValue) - adjustmentValue;
            genes.set(index, genes.get(index) * randomAdjustmentValue);
        }
    }

    public GeneticCode crossBreed(GeneticCode secondaryGenes) {
        int lowerGeneCount = secondaryGenes.getGeneCount();
        GeneticCode biggerGeneticCode = secondaryGenes;
        int higherGeneCount;
        if (lowerGeneCount > geneCount) {
            higherGeneCount = lowerGeneCount;
            lowerGeneCount = geneCount;
            biggerGeneticCode = this;
        } else {
            higherGeneCount = geneCount;
        }
        ArrayList<Double> newGenes = new ArrayList<>(higherGeneCount);
        for (int i = 0; i < lowerGeneCount; i++) {
            newGenes.set(i, Math.random() < 0.5 ? this.getGene(i) : secondaryGenes.getGene(i));
        }
        if (lowerGeneCount != higherGeneCount) {
            for (int i = lowerGeneCount; i < higherGeneCount; i++) {
                newGenes.set(i, biggerGeneticCode.getGene(i));
            }
        }
        return new GeneticCode(newGenes,higherGeneCount);
    }

    public GeneticCode deepCopy() {
        return new GeneticCode((ArrayList<Double>) genes.clone());
    }
}
