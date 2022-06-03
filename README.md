# moqi-java-performance-tuning

## Environment

* [JMH: Java Microbenchmark Harness](https://github.com/openjdk/jmh)
* [Plugin: Intellij IDEA plugin for Java Microbenchmark Harness (JMH)](https://github.com/artyushov/idea-jmh-plugin)

## Roadmap

### string-intern-benchmark

#### 20220603133040

```text
# JMH version: 1.21
# VM version: JDK 1.8.0_332, OpenJDK 64-Bit Server VM, 25.332-b09
# VM invoker: /Users/moqi/Library/Java/JavaVirtualMachines/temurin-1.8.0_332/Contents/Home/jre/bin/java
# VM options: -javaagent:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/221.5787.30/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=60455:/Users/moqi/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/221.5787.30/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: net.sdo.StringInternTest.testIntern
# Parameters: (cacheHit100 = true, nStrings = 1)

# Run progress: 0.00% complete, ETA 00:08:20
# Fork: 1 of 5
# Warmup Iteration   1: ≈ 10⁻⁶ s/op
# Warmup Iteration   2: ≈ 10⁻⁶ s/op
# Warmup Iteration   3: ≈ 10⁻⁶ s/op
# Warmup Iteration   4: ≈ 10⁻⁶ s/op
# Warmup Iteration   5: ≈ 10⁻⁷ s/op
Iteration   1: ≈ 10⁻⁶ s/op
Iteration   2: ≈ 10⁻⁶ s/op
Iteration   3: ≈ 10⁻⁷ s/op
Iteration   4: ≈ 10⁻⁷ s/op
Iteration   5: ≈ 10⁻⁷ s/op

# Run progress: 20.00% complete, ETA 00:06:43
# Fork: 2 of 5
# Warmup Iteration   1: ≈ 10⁻⁶ s/op
# Warmup Iteration   2: ≈ 10⁻⁶ s/op
# Warmup Iteration   3: ≈ 10⁻⁶ s/op
# Warmup Iteration   4: ≈ 10⁻⁷ s/op
# Warmup Iteration   5: ≈ 10⁻⁶ s/op
Iteration   1: ≈ 10⁻⁷ s/op
Iteration   2: ≈ 10⁻⁶ s/op
Iteration   3: ≈ 10⁻⁶ s/op
Iteration   4: ≈ 10⁻⁶ s/op
Iteration   5: ≈ 10⁻⁶ s/op

# Run progress: 40.00% complete, ETA 00:05:02
# Fork: 3 of 5
# Warmup Iteration   1: ≈ 10⁻⁶ s/op
# Warmup Iteration   2: ≈ 10⁻⁶ s/op
# Warmup Iteration   3: ≈ 10⁻⁶ s/op
# Warmup Iteration   4: ≈ 10⁻⁷ s/op
# Warmup Iteration   5: ≈ 10⁻⁷ s/op
Iteration   1: ≈ 10⁻⁶ s/op
Iteration   2: ≈ 10⁻⁷ s/op
Iteration   3: ≈ 10⁻⁶ s/op
Iteration   4: ≈ 10⁻⁶ s/op
Iteration   5: ≈ 10⁻⁶ s/op

# Run progress: 60.00% complete, ETA 00:03:21
# Fork: 4 of 5
# Warmup Iteration   1: ≈ 10⁻⁶ s/op
# Warmup Iteration   2: ≈ 10⁻⁶ s/op
# Warmup Iteration   3: ≈ 10⁻⁷ s/op
# Warmup Iteration   4: ≈ 10⁻⁶ s/op
# Warmup Iteration   5: ≈ 10⁻⁶ s/op
Iteration   1: ≈ 10⁻⁶ s/op
Iteration   2: ≈ 10⁻⁶ s/op
Iteration   3: ≈ 10⁻⁷ s/op
Iteration   4: ≈ 10⁻⁶ s/op
Iteration   5: ≈ 10⁻⁶ s/op

# Run progress: 80.00% complete, ETA 00:01:40
# Fork: 5 of 5
# Warmup Iteration   1: ≈ 10⁻⁷ s/op
# Warmup Iteration   2: ≈ 10⁻⁶ s/op
# Warmup Iteration   3: ≈ 10⁻⁷ s/op
# Warmup Iteration   4: ≈ 10⁻⁶ s/op
# Warmup Iteration   5: ≈ 10⁻⁶ s/op
Iteration   1: ≈ 10⁻⁷ s/op
Iteration   2: ≈ 10⁻⁶ s/op
Iteration   3: ≈ 10⁻⁶ s/op
Iteration   4: ≈ 10⁻⁶ s/op
Iteration   5: ≈ 10⁻⁶ s/op


Result "net.sdo.StringInternTest.testIntern":
  ≈ 10⁻⁶ s/op


# Run complete. Total time: 00:08:24

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                    (cacheHit100)  (nStrings)  Mode  Cnt   Score    Error  Units
StringInternTest.testIntern           true           1  avgt   25  ≈ 10⁻⁶            s/op
```
