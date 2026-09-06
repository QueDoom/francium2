package net.quedoom.francium.mixin;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.quedoom.francium.Francium;
import net.quedoom.francium.block.ModdedBambooStalkBlock;
import net.quedoom.francium.block.SolidSugarCaneBlock;
import net.quedoom.francium.item.vanilla.StickItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Blocks.class)
public class RegisterBlockMixin {
    @Inject(
            method = "register(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;",
            at =  @At(value = "HEAD"),
            cancellable = true)

    private static void register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<Block> cir) {
        String name = id.toString();
        if (name.equals(string("bedrock"))) {
            BlockBehaviour.Properties customProperty = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(10.0F, 3600000.0F).isValidSpawn(Blocks::never).requiresCorrectToolForDrops();
            Block block = factory.apply(customProperty.setId(id));
            cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
        }

        if (name.equals(string("sugar_cane"))) {
            BlockBehaviour.Properties customProperty = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().sound(SoundType.BAMBOO).pushReaction(PushReaction.DESTROY).strength(2f);
            Function<BlockBehaviour.Properties, Block> newFactory = SolidSugarCaneBlock::new;
            Block block = newFactory.apply(customProperty.setId(id));
            cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
        }

        if (name.equals(string("bamboo"))) {
            BlockBehaviour.Properties customProperty = BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .forceSolidOn()
                    .randomTicks()
                    .strength(1.0F)
                    .sound(SoundType.BAMBOO)
                    .noOcclusion()
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never);

            Function<BlockBehaviour.Properties, Block> newFactory = ModdedBambooStalkBlock::new;
            Block block = newFactory.apply(customProperty.setId(id));
            cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
        }

//        if (name.equals("bedrock")) {
//            BlockBehaviour.Properties customProperty = BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(10.0F, 3600000.0F).isValidSpawn(Blocks::never).requiresCorrectToolForDrops();
//            Block block = factory.apply(customProperty.setId(id));
//            cir.setReturnValue(Registry.register(BuiltInRegistries.BLOCK, id, block));
//        }
    }

    @Unique
    private static String string(String string) {
        return "ResourceKey[minecraft:block / minecraft:" + string + "]";
    }
    @Unique
    private static String string(String namespace, String string) {
        return "ResourceKey[minecraft:block / " + namespace + ":" + string + "]";
    }
    @Unique
    private static String string(Identifier identifier) {
        return "ResourceKey[minecraft:block / " + identifier.getNamespace() + ":" +  identifier.getPath() + "]";
    }
}
