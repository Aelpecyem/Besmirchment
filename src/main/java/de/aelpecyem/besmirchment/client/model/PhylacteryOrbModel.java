// Made with Blockbench 3.8.0
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package de.aelpecyem.besmirchment.client.model;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class PhylacteryOrbModel extends Model {
    public final ModelPart orbiter1;
	public final ModelPart orbiter2;
	public final ModelPart orbiter3;

    public PhylacteryOrbModel() {
    	super(RenderLayer::getEntityCutout);
        textureWidth = 32;
        textureHeight = 32;
        orbiter1 = new ModelPart(this);
        orbiter1.setPivot(0.0F, 10.0F, 0.0F);
        orbiter1.setTextureOffset(15, 2).addCuboid(-5.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        orbiter1.setTextureOffset(15, 2).addCuboid(6.0F, 4.0F, 2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        orbiter2 = new ModelPart(this);
        orbiter2.setPivot(0.0F, 13.0F, 0.0F);
        setRotationAngle(orbiter2, 0.0F, -1.5708F, 0.0F);
        orbiter2.setTextureOffset(15, 2).addCuboid(-8.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        orbiter2.setTextureOffset(15, 2).addCuboid(7.0F, 4.0F, 2.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        orbiter3 = new ModelPart(this);
        orbiter3.setPivot(0.0F, 13.0F, 0.0F);
        setRotationAngle(orbiter3, 0.0F, 1.5708F, 0.0F);
        orbiter3.setTextureOffset(15, 2).addCuboid(-7.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        orbiter3.setTextureOffset(15, 2).addCuboid(4.0F, 4.0F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        orbiter1.render(matrixStack, buffer, packedLight, packedOverlay);
        orbiter2.render(matrixStack, buffer, packedLight, packedOverlay);
        orbiter3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}