/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.sdo;

import extra166y.CustomConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.RandomStringUtils;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class StringInternTest {

    @Param({"1"})
    int nStrings;

    @Param({"true"})
    boolean cacheHit100;

    private String[] strings;
    private ConcurrentHashMap<String,String> smap;
    private CustomConcurrentHashMap<String,String> wmap;

    @Setup(Level.Trial)
    public void setupGlobal() {
	smap = new ConcurrentHashMap<>();
	wmap = new CustomConcurrentHashMap<>(
			CustomConcurrentHashMap.WEAK,
			CustomConcurrentHashMap.EQUALS,
			CustomConcurrentHashMap.WEAK,
			CustomConcurrentHashMap.EQUALS,
			60013);
    }

    @Setup(Level.Iteration)
    public void setup() {
	if (cacheHit100) {
	    strings = new String[nStrings];
	    for (int i = 0; i < nStrings; i++) {
	        strings[i] = makeRandomString();
	    }
	}
    }

    @Benchmark
    public void testIntern(Blackhole bh) {
	for (int i = 0; i < nStrings; i++) {
	    String t = (cacheHit100) ? strings[i].intern() : makeRandomString();
	    bh.consume(t);
	}
    }

    @Benchmark
    public void testStrongMap(Blackhole bh) {
	for (int i = 0; i < nStrings; i++) {
	    String t = (cacheHit100) ? strings[i].intern() : makeRandomString();
	    t = smap.putIfAbsent(t, t);
	    bh.consume(t);
	}
    }

    @Benchmark
    public void testWeakMap(Blackhole bh) {
	for (int i = 0; i < nStrings; i++) {
	    String t = (cacheHit100) ? strings[i].intern() : makeRandomString();
	    t = wmap.putIfAbsent(t, t);
	    bh.consume(t);
	}
    }

    private String makeRandomString() {
	return RandomStringUtils.randomAscii(5, 256);
    }
}
