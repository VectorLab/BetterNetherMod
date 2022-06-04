package org.betterx.betternether.registry.features;

import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import org.betterx.bclib.api.features.BCLFeature;
import org.betterx.bclib.api.features.BCLFeatureBuilder;
import org.betterx.bclib.api.features.placement.FindSolidInDirection;
import org.betterx.bclib.api.features.placement.OnEveryLayer;
import org.betterx.betternether.BetterNether;

import java.util.List;

public class BiomeFeatures {
    public static final List<PlacementModifier> defaultCeilModifiers = List.of(CountPlacement.of(16),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_4_4,
            FindSolidInDirection.up(12),
            BiomeFilter.biome());
    public static final List<PlacementModifier> defaultFloorModifiers = List.of(CountPlacement.of(16),
            InSquarePlacement.spread(),
            OnEveryLayer.min4(),
            BiomeFilter.biome());

    public static final List<PlacementModifier> defaultWallModifiers = List.of(CountPlacement.of(16),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_4_4,
            new FindSolidInDirection(List.of(Direction.WEST, Direction.EAST, Direction.NORTH, Direction.SOUTH), 12),
            BiomeFilter.biome());

    public static final BCLFeature MAGMA_LAND_FLOOR = BCLFeatureBuilder
            .start(BetterNether.makeID("magma_land_floor"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultFloorModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(FloorFeatures.GEYSER.getPlacedFeature(), 0.02f),
                    new WeightedPlacedFeature(FloorFeatures.MAGMA_FLOWER.getPlacedFeature(), 0.1f),
                    new WeightedPlacedFeature(FloorFeatures.CRYSTAL.getPlacedFeature(), 0.03f),
                    new WeightedPlacedFeature(FloorFeatures.BLACK_BUSH_PATCH.getPlacedFeature(), 0.03f)
            ), FloorFeatures.CRYSTAL.getPlacedFeature()));

    public static final BCLFeature MAGMA_LAND_CEIL = BCLFeatureBuilder
            .start(BetterNether.makeID("magma_land_ceil"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultCeilModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(),
                    VineLikeFeatures.GOLDEN_VINE.getPlacedFeature()));

    public static final BCLFeature BONE_REEF_FLOOR = BCLFeatureBuilder
            .start(BetterNether.makeID("bone_reef_floor"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultFloorModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(FloorFeatures.BONES.getPlacedFeature(), 0.015f),
                    new WeightedPlacedFeature(FloorFeatures.BONE_GRASS_PATCH.getPlacedFeature(), 0.10f),
                    new WeightedPlacedFeature(FloorFeatures.FEATHER_FERN_PATCH.getPlacedFeature(), 0.05f),
                    new WeightedPlacedFeature(FloorFeatures.JELLYFISH_MUSHROOM.getPlacedFeature(), 0.01f),
                    new WeightedPlacedFeature(VineLikeFeatures.NETHER_REED.getPlacedFeature(), 0.03f),
                    new WeightedPlacedFeature(VineLikeFeatures.STALAGMITE_BONE_CLUSTER.getPlacedFeature(), 0.01f)
            ), FloorFeatures.BONE_GRASS_PATCH.getPlacedFeature()));

    public static final BCLFeature BONE_REEF_CEIL = BCLFeatureBuilder
            .start(BetterNether.makeID("bone_reef_ceil"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultCeilModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(),
                    VineLikeFeatures.LUMBUS_VINE.getPlacedFeature()));

    public static final BCLFeature SOUL_PLAIN_FLOOR = BCLFeatureBuilder
            .start(BetterNether.makeID("soul_plain_floor"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultFloorModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(VineLikeFeatures.SOUL_VEIN.getPlacedFeature(), 0.3f),
                    new WeightedPlacedFeature(FloorFeatures.SOUL_GRASS_PATCH.getPlacedFeature(), 0.2f),
                    new WeightedPlacedFeature(FloorFeatures.BLACK_BUSH_PATCH.getPlacedFeature(), 0.2f)
            ), VineLikeFeatures.SOUL_VEIN.getPlacedFeature()));


    public static final BCLFeature GRAVEL_DESERT_FLOOR = BCLFeatureBuilder
            .start(BetterNether.makeID("gravel_desert_floor"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultFloorModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(FloorFeatures.AGAVE.getPlacedFeature(), 0.5f),
                    new WeightedPlacedFeature(FloorFeatures.BARREL_CACTUS.getPlacedFeature(), 0.1f),
                    new WeightedPlacedFeature(VineLikeFeatures.NETHER_CACTUS.getPlacedFeature(), 0.2f)
            ), FloorFeatures.AGAVE.getPlacedFeature()));

    public static final BCLFeature CRIMSON_GLOWING_WOODS_FLOOR = BCLFeatureBuilder
            .start(BetterNether.makeID("crimson_glowing_woods_floor"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultFloorModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(TreeFeatures.CRIMSON_GLOWING.getPlacedFeature(), 0.2f),
                    new WeightedPlacedFeature(FloorFeatures.CRIMSON_ROOTS.getPlacedFeature(), 0.3f),
                    new WeightedPlacedFeature(FloorFeatures.CRIMSON_FUNGUS.getPlacedFeature(), 0.2f),
                    new WeightedPlacedFeature(FloorFeatures.WART_BUSH_PATCH.getPlacedFeature(), 0.15f),
                    new WeightedPlacedFeature(FloorFeatures.WART_SEED_PATCH.getPlacedFeature(), 0.15f)
            ), FloorFeatures.CRIMSON_ROOTS.getPlacedFeature()));

    public static final BCLFeature CRIMSON_GLOWING_WOODS_CEIL = BCLFeatureBuilder
            .start(BetterNether.makeID("crimson_glowing_woods_ceil"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultCeilModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(VineLikeFeatures.GOLDEN_VINE.getPlacedFeature(), 0.02f)
            ), VineLikeFeatures.GOLDEN_VINE.getPlacedFeature()));

    public static final BCLFeature CRIMSON_GLOWING_WOODS_WALL = BCLFeatureBuilder
            .start(BetterNether.makeID("crimson_glowing_woods_wall"), BCLFeature.RANDOM_SELECTOR)
            .decoration(GenerationStep.Decoration.VEGETAL_DECORATION)
            .modifier(defaultWallModifiers)
            .buildAndRegister(new RandomFeatureConfiguration(List.of(
                    new WeightedPlacedFeature(WallFeatures.WALL_MOSS.getPlacedFeature(), 0.4f),
                    new WeightedPlacedFeature(WallFeatures.WALL_MUSHROOM_RED.getPlacedFeature(), 0.6f)
            ), WallFeatures.WALL_MUSHROOM_RED.getPlacedFeature()));


    public static void ensureStaticInitialization() {
    }
}