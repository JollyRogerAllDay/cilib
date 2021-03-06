/**
 * Computational Intelligence Library (CIlib)
 * Copyright (C) 2003 - 2010
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.cilib.pso.pbestupdate;

import net.sourceforge.cilib.entity.EntityType;
import net.sourceforge.cilib.entity.Particle;
import net.sourceforge.cilib.problem.solution.InferiorFitness;
import net.sourceforge.cilib.type.types.Types;

/**
 * Update the personal best of the particle, if it is a valid update. Valid updates are
 * defined to be only within the problem search space. Any particle drifting into an
 * infeasible part of the search space will be allowed to do so, however, any solutions
 * found will not allowed to become personal best positions.
 *
 */
public class BoundedPersonalBestUpdateStrategy implements PersonalBestUpdateStrategy {

    private static final long serialVersionUID = -3574938411781908840L;
    private PersonalBestUpdateStrategy delegate;

    public BoundedPersonalBestUpdateStrategy() {
        this.delegate = new StandardPersonalBestUpdateStrategy();
    }

    public BoundedPersonalBestUpdateStrategy(BoundedPersonalBestUpdateStrategy copy) {
        this.delegate = copy.delegate.getClone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonalBestUpdateStrategy getClone() {
        return new BoundedPersonalBestUpdateStrategy(this);
    }

    /**
     * Update personal best if and only if the particle is within the bounds of the
     * search space / problem.
     * @param particle The particle to update.
     */
    @Override
    public void updatePersonalBest(Particle particle) {
        if (!Types.isInsideBounds(particle.getPosition())) {
            particle.getProperties().put(EntityType.FITNESS, InferiorFitness.instance());
            return;
        }

        delegate.updatePersonalBest(particle);
    }

    public void setDelegate(PersonalBestUpdateStrategy delegate) {
        this.delegate = delegate;
    }

    public PersonalBestUpdateStrategy getDelegate() {
        return delegate;
    }
}
