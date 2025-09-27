import subprocess
from pathlib import Path
import pandas as pd
import matplotlib.pyplot as plt

PROJECT = Path("/Users/a1d1yar/Desktop/assignment2-daa-selection-sort")
CSV     = PROJECT / "docs" / "results.csv"
PLOTS   = PROJECT / "docs" / "plots"

NS      = [100, 1000, 10_000, 100_000]
DISTS   = ["random", "sorted", "reverse", "nearly"]
TRIALS  = 3
SHOW_PLOTS = True

(PROJECT / "docs").mkdir(exist_ok=True)
PLOTS.mkdir(exist_ok=True)

print("🔧 Building Java project...")
subprocess.run(["mvn", "-q", "-DskipTests", "package"], cwd=PROJECT, check=True)

java_cmd = ["java", "-cp", "target/classes", "app.BenchmarkRunner"]
print("🏃 Running benchmarks...")
for dist in DISTS:
    for n in NS:
        print(f"  → {dist:7s} n={n} trials={TRIALS}")
        subprocess.run(
            java_cmd + ["--n", str(n), "--trials", str(TRIALS),
                        "--dist", dist, "--csv", "docs/results.csv"],
            cwd=PROJECT, check=True
        )

print(f"✅ Data collected: {CSV}")

print("📈 Plotting...")

df = pd.read_csv(CSV)
# усредним по trials
g = df.groupby(["dist", "n"], as_index=False)["time_ms"].mean()

# 3.1 общий график
plt.figure()
for dist, sub in g.groupby("dist"):
    sub = sub.sort_values("n")
    plt.plot(sub["n"], sub["time_ms"], marker="o", label=dist)
plt.xlabel("Input size (n)")
plt.ylabel("Time (ms)")
plt.title("Selection Sort — Time vs n (mean over trials)")
plt.legend()
plt.grid(True)
plt.tight_layout()
main_png = PLOTS / "time_vs_n_all.png"
plt.savefig(main_png, dpi=200)
if SHOW_PLOTS: plt.show()
print(f"✅ Saved: {main_png}")

for dist, sub in g.groupby("dist"):
    sub = sub.sort_values("n")
    plt.figure()
    plt.plot(sub["n"], sub["time_ms"], marker="o")
    plt.xlabel("Input size (n)")
    plt.ylabel("Time (ms)")
    plt.title(f"Selection Sort — {dist}")
    plt.grid(True)
    plt.tight_layout()
    out = PLOTS / f"time_vs_n_{dist}.png"
    plt.savefig(out, dpi=200)
    if SHOW_PLOTS: plt.show()
    print(f"✅ Saved: {out}")

pivot = g.pivot(index="n", columns="dist", values="time_ms").sort_index()
pivot_csv = PLOTS / "pivot_time_ms.csv"
pivot.to_csv(pivot_csv)
print(f"✅ Saved table: {pivot_csv}")

print("🎯 Done.")