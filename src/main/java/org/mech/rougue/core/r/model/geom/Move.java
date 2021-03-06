package org.mech.rougue.core.r.model.geom;

import org.mech.terminator.geometry.Position;

public enum Move {
    E(-1, 0), N(0, -1), S(0, 1), W(1, 0), S_E(-1, 1), N_E(-1, -1), S_W(1, 1), N_W(1, -1);

    int x, y;

    private Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Position shift(final Position p) {
        return shift(p, 1);
    }

    public Position shift(final Position p, final int n) {
        final int x = p.x + (this.x() * n);
        final int y = p.y + (this.y() * n);
        return new Position(x, y);
    }

}
