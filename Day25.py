from utils import *

with open("input.txt") as f:
    a = f.read().strip().split('\n\n')

ls = [[sum(row[col] == '#' for row in i.splitlines()) - 1 for col in range(5)] for i in a if i[0] == '#']
ks = [[sum(row[col] == '#' for row in i.splitlines()) - 1 for col in range(5)] for i in a if i[0] != '#']

print(sum(all(i + j < 6 for i, j in zip(l, k)) for l in ls for k in ks))
