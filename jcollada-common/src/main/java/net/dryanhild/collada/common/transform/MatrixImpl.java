/*
 * Copyright (c) 2014 D. Ryan Hild <d.ryan.hild@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.dryanhild.collada.common.transform;

import lombok.Getter;
import lombok.Setter;
import net.dryanhild.collada.data.transform.Matrix;

import java.nio.FloatBuffer;
import java.util.Arrays;

@Getter
@Setter
public class MatrixImpl implements Matrix {

    public final float[] values = new float[16];
    private String sid;

    @Override
    public float[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public void setValues(float[] values) {
        assert values.length == 16;
        System.arraycopy(values, 0, this.values, 0, 16);
    }

    @Override
    public Matrix toMatrix() {
        return this;
    }

    @Override
    public void putAsColumnMatrix(FloatBuffer buffer) {
        buffer.put(values);
    }
}
