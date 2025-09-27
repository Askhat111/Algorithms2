from pathlib import Path
import pandas as pd
import matplotlib.pyplot as plt

CSV = Path("/Users/a1d1yar/Desktop/assignment2-daa-selection-sort/docs/results.csv")
PLOTS = CSV.parent / "plots"
PLOTS.mkdir(exist_ok=True)

print("Reading:", CSV)
df = pd.read_csv(CSV)

g = df.groupby(["dist", "n"], as_index=False)["time_ms"].mean()

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
out = PLOTS / "time_vs_n_all.png"
plt.savefig(out, dpi=200)
print("Saved plot:", out)