/**
 * Copyright 2004-2006 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * Permission is hereby granted, free of charge, to use and distribute
 * this software and its documentation without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of this work, and to
 * permit persons to whom this work is furnished to do so, subject to
 * the following conditions:
 * 
 * 1. The code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 * 2. Any modifications must be clearly marked as such.
 * 3. Original authors' names are not deleted.
 * 4. The authors' names are not used to endorse or promote products
 *    derived from this software without specific prior written
 *    permission.
 *
 * DFKI GMBH AND THE CONTRIBUTORS TO THIS WORK DISCLAIM ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL DFKI GMBH NOR THE
 * CONTRIBUTORS BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL
 * DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR
 * PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 * ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */

package marytts.signalproc.tests;

import javax.sound.sampled.AudioFormat;

import marytts.util.data.BufferedDoubleDataSource;
import marytts.util.data.audio.AudioDoubleDataSource;
import marytts.util.data.audio.DDSAudioInputStream;
import marytts.util.math.MathUtils;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Marc Schr&ouml;der
 *
 */
public class AudioDoubleDataSourceTest extends TestCase
{

    public void testGetAllData1()
    {
        int samplingRate = 16000;
        AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, samplingRate, 16, 1, 2, samplingRate, false);
        double[] testSignal = FFTTest.getSampleSignal(16000);
        DDSAudioInputStream ais = new DDSAudioInputStream(new BufferedDoubleDataSource(testSignal), af);
        double [] result = new AudioDoubleDataSource(ais).getAllData();
        Assert.assertTrue(result.length == testSignal.length);
    }

    public void testGetAllData2()
    {
        int samplingRate = 16000;
        AudioFormat af = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, samplingRate, 16, 1, 2, samplingRate, false);
        double[] signal = FFTTest.getSampleSignal(16000);
        DDSAudioInputStream ais = new DDSAudioInputStream(new BufferedDoubleDataSource(signal), af);
        double [] result = new AudioDoubleDataSource(ais).getAllData();
        double err = MathUtils.sumSquaredError(signal, result);
        Assert.assertTrue("Error: "+err, err<1.E-20);
    }

}
