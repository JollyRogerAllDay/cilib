/*
 * MultiPopulationBasedAlgorithm.java
 * 
 * Created on Feb 10, 2006
 *
 * Copyright (C) 2003 - 2006 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package net.sourceforge.cilib.algorithm.population;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.cooperative.algorithmiterators.AlgorithmIterator;
import net.sourceforge.cilib.cooperative.algorithmiterators.SequentialAlgorithmIterator;
import net.sourceforge.cilib.entity.visitor.TopologyVisitor;

/**
 * @author Gary Pampara
 */
public abstract class MultiPopulationBasedAlgorithm extends PopulationBasedAlgorithm implements Iterable<Algorithm> {
	protected List<PopulationBasedAlgorithm> subPopulationsAlgorithms;
	protected AlgorithmIterator<PopulationBasedAlgorithm> algorithmIterator;

	public MultiPopulationBasedAlgorithm() {
		this.subPopulationsAlgorithms = new ArrayList<PopulationBasedAlgorithm>();
		this.algorithmIterator = new SequentialAlgorithmIterator<PopulationBasedAlgorithm>();
		this.algorithmIterator.setAlgorithms(this.subPopulationsAlgorithms);
	}

	/**
	 * @author Theuns Cloete
	 * @param rhs The
	 *        {@linkplain net.sourceforge.cilib.algorithm.population.MultiPopulationBasedAlgorithm}
	 *        that should be copied.
	 */
	@SuppressWarnings("unchecked")
	public MultiPopulationBasedAlgorithm(MultiPopulationBasedAlgorithm rhs) {
		super(rhs);
		subPopulationsAlgorithms = new ArrayList<PopulationBasedAlgorithm>();
		for (PopulationBasedAlgorithm algorithm : rhs.subPopulationsAlgorithms) {
			subPopulationsAlgorithms.add(algorithm.clone());
		}
		algorithmIterator = rhs.algorithmIterator;
		algorithmIterator.setAlgorithms(subPopulationsAlgorithms);
	}

	public void reset() {
		super.reset();
		for(Algorithm algorithm : subPopulationsAlgorithms)
			algorithm.reset();
		algorithmIterator.setAlgorithms(subPopulationsAlgorithms);
	}

	@SuppressWarnings("unchecked")
	public Iterator<Algorithm> iterator() {
		return this.algorithmIterator.clone();
	}

	@Override
	protected abstract void algorithmIteration();

	public List<PopulationBasedAlgorithm> getPopulations() {
		return subPopulationsAlgorithms;
	}

	public void setPopulations(List<PopulationBasedAlgorithm> populationBasedAlgorithms) {
		this.subPopulationsAlgorithms = populationBasedAlgorithms;
	}

	public void addPopulationBasedAlgorithm(PopulationBasedAlgorithm algorithm) {
		this.subPopulationsAlgorithms.add(algorithm);
	}

	public void removePopulationBasedalgorithm(PopulationBasedAlgorithm algorithm) {
		this.subPopulationsAlgorithms.remove(algorithm);
	}

	public AlgorithmIterator<PopulationBasedAlgorithm> getAlgorithmIterator() {
		return algorithmIterator;
	}

	public void setAlgorithmIterator(AlgorithmIterator<PopulationBasedAlgorithm> algorithmIterator) {
		this.algorithmIterator = algorithmIterator;
		this.algorithmIterator.setAlgorithms(this.subPopulationsAlgorithms);
	}

	@Override
	public double accept(TopologyVisitor visitor) {
		throw new UnsupportedOperationException("Needs an implementation");
	}

}
