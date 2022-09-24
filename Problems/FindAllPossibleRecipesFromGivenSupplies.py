# You have information about n different recipes. 
# You are given a string array recipes and a 2D string array ingredients. 
# The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. 
# Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
#
# You are also given a string array supplies containing all the ingredients that you initially have, 
# and you have an infinite supply of all of them.
#
# Return a list of all the recipes that you can create. You may return the answer in any order.
#
# Note that two recipes may contain each other in their ingredients.

from collections import Counter, defaultdict, deque
from typing import List

# solution using topological sort
def findAllRecipes(recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
    available = set(supplies)
    ans, ingredient_to_recipe, in_degree = [], defaultdict(set), Counter()
    for rcp, ingredient in zip(recipes, ingredients):
        non_available = 0
        for ing in ingredient:
            if ing not in available:
                non_available += 1
                ingredient_to_recipe[ing].add(rcp)
        if non_available == 0:
            ans.append(rcp)
        else:
            in_degree[rcp] = non_available
    for rcp in ans:
        for recipe in ingredient_to_recipe.pop(rcp, set()):
            in_degree[recipe] -= 1
            if in_degree[recipe] == 0:
                ans.append(recipe)
    return ans

# another solution with topological sort
def findAllRecipes2(recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
    indeg = defaultdict(int)
    graph = defaultdict(list)
    for r, ing in zip(recipes, ingredients): 
        indeg[r] = len(ing)
        for i in ing: graph[i].append(r)
    
    ans = []
    queue = deque(supplies)
    recipes = set(recipes)
    while queue:
        x = queue.popleft()
        if x in recipes: ans.append(x)
        for xx in graph[x]:
            indeg[xx] -= 1
            if indeg[xx] == 0: queue.append(xx)
    return ans

recipes = ["bread","sandwich","burger"]
ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]]
supplies = ["yeast","flour","meat"]
print(findAllRecipes(recipes, ingredients, supplies))