package paulevs.betternether.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import paulevs.betternether.BetterNether;
import paulevs.betternether.IRecipeManager;

public class BNRecipeManager
{
	private static final Map<RecipeType<?>, Map<Identifier, Recipe<?>>> RECIPES = Maps.newHashMap();
	
	public static void addRecipe(RecipeType<?> type, Recipe<?> recipe)
	{
		Map<Identifier, Recipe<?>> list = RECIPES.get(type);
		if (list == null)
		{
			list = Maps.newHashMap();
			RECIPES.put(type, list);
		}
		list.put(recipe.getId(), recipe);
	}
	
	public static void inject(RecipeManager recipeManager)
	{
		IRecipeManager manager = (IRecipeManager) recipeManager;
		
		Map<RecipeType<?>, Map<Identifier, Recipe<?>>> recipes = manager.getMap();
		Map<RecipeType<?>, Map<Identifier, Recipe<?>>> result = Maps.newHashMap();
		
		for (RecipeType<?> type: RECIPES.keySet())
		{
			Map<Identifier, Recipe<?>> newList = Maps.newHashMap();
			Map<Identifier, Recipe<?>> list = RECIPES.get(type);
			
			Map<Identifier, Recipe<?>> originalList = recipes.get(type);
			if (originalList.isEmpty())
				newList.putAll(originalList);
			
			if (list != null)
				newList.putAll(list);
			
			result.put(type, newList);
		}
		
		manager.setMap(result);
	}
	
	public static DefaultedList<Ingredient> getIngredients(String[] pattern, Map<String, Ingredient> key, int width, int height)
	{
		DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(key.keySet());
		set.remove(" ");

		for(int i = 0; i < pattern.length; ++i) {
			for(int j = 0; j < pattern[i].length(); ++j) {
				String string = pattern[i].substring(j, j + 1);
				Ingredient ingredient = (Ingredient)key.get(string);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
				}

				set.remove(string);
				defaultedList.set(j + width * i, ingredient);
			}
		}

		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return defaultedList;
		}
	}
	
	public static void addCraftingRecipe(String name, String[] shape, Map<String, ItemStack> materials, ItemStack result)
	{
		int width = shape[0].length();
		int height = shape.length;
		
		Map<String, Ingredient> mapIng = new HashMap<String, Ingredient>();
		materials.forEach((id, material) -> {
			mapIng.put(id, Ingredient.ofStacks(material));
		});
		
		DefaultedList<Ingredient> list = BNRecipeManager.getIngredients(shape, mapIng, width, height);
		ShapedRecipe recipe = new ShapedRecipe(new Identifier(BetterNether.MOD_ID, name), "", width, height, list, result);
		BNRecipeManager.addRecipe(RecipeType.CRAFTING, recipe);
	}
}