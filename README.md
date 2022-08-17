# BALL-TREE-PROJECT

Balltree is a simple geometric space partitioning data structure used to partition data points in n-dimensional space.
Balltree is a complete binary tree where each node represents an n-dimensional hypersphere called “ball”. Each internal node represents the minor ball containing the balls of its children and partitions the data points into two disjoint sets. The hypersphere(balls) may intersect each other but points are assigned to a particular ball depending on distance from the centers of balls.
Ball trees have a broad range of applications in geometric learning tasks, nearest neighbor retrieval, intersection and constraint queries, and probability maximization.
