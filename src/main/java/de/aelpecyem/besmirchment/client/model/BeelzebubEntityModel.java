// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package de.aelpecyem.besmirchment.client.model;

import de.aelpecyem.besmirchment.common.entity.BeelzebubEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

public class BeelzebubEntityModel<T extends BeelzebubEntity> extends BipedEntityModel<BeelzebubEntity> {
    private final ModelPart chest;
    private final ModelPart chest2_r1;
    private final ModelPart stomach;
    private final ModelPart fSkirt01;
    private final ModelPart lSkirt01;
    private final ModelPart lSkirt02;
    private final ModelPart rSkirt01;
    private final ModelPart rSkirt02;
    private final ModelPart bSkirt01;
    private final ModelPart fSkirt02;
    private final ModelPart lFLeg01;
    private final ModelPart lFLeg02;
    private final ModelPart lFLeg03;
    private final ModelPart lFLegClaw01;
    private final ModelPart lFLegClaw02;
    private final ModelPart lFLegSpur;
    private final ModelPart lBLeg01;
    private final ModelPart lBLeg02;
    private final ModelPart Box_r1;
    private final ModelPart lBLeg03;
    private final ModelPart Box_r2;
    private final ModelPart lBLegClaw01;
    private final ModelPart Box_r3;
    private final ModelPart lBLegClaw02;
    private final ModelPart Box_r4;
    private final ModelPart lBLegSpur;
    private final ModelPart Box_r5;
    private final ModelPart rBLeg01;
    private final ModelPart rBLeg02;
    private final ModelPart Box_r6;
    private final ModelPart rBLeg03;
    private final ModelPart Box_r7;
    private final ModelPart rBLegClaw01;
    private final ModelPart Box_r8;
    private final ModelPart rBLegClaw02;
    private final ModelPart Box_r9;
    private final ModelPart rBLegSpur;
    private final ModelPart Box_r10;
    private final ModelPart rFLeg01;
    private final ModelPart rFLeg02;
    private final ModelPart rFLeg03;
    private final ModelPart rFLegClaw01;
    private final ModelPart rFLeg2;
    private final ModelPart rFLegSpur;
    private final ModelPart hoodLTop;
    private final ModelPart hoodRTop;
    private final ModelPart hoodLSide01;
    private final ModelPart hoodRSide01;
    private final ModelPart hoodLSide02;
    private final ModelPart hoodRSide02;
    private final ModelPart hoodPipe01;
    private final ModelPart hoodPipe02;
    private final ModelPart lEye;
    private final ModelPart rEye;
    private final ModelPart hoodBack;
    private final ModelPart lAntenna01;
    private final ModelPart lAntenna02;
    private final ModelPart lAntenna03;
    private final ModelPart lAntenna04;
    private final ModelPart lAntenna05;
    private final ModelPart lAntenna06;
    private final ModelPart rAntenna01;
    private final ModelPart rAntenna02;
    private final ModelPart rAntenna03;
    private final ModelPart rAntenna04;
    private final ModelPart rAntenna05;
    private final ModelPart rAntenna06;
    private final ModelPart chestCloth;
    private final ModelPart lArm01;
    private final ModelPart lArm02;
    private final ModelPart lSleeve01;
    private final ModelPart lSleeve02;
    private final ModelPart lArm03;
    private final ModelPart lClaw01;
    private final ModelPart lClaw02;
    private final ModelPart rArm01;
    private final ModelPart rArm02;
    private final ModelPart rSleeve01;
    private final ModelPart rSleeve02;
    private final ModelPart rArm03;
    private final ModelPart rClaw01;
    private final ModelPart rClaw02;
    private final ModelPart lWing01;
    private final ModelPart lWing02;
    private final ModelPart lWing03;
    private final ModelPart lWingMembrane;
    private final ModelPart lWingLower01;
    private final ModelPart lWingLower02;
    private final ModelPart lWingLower03;
    private final ModelPart lWingLowerMemebrane;
    private final ModelPart rWingLower01;
    private final ModelPart rWingLower02;
    private final ModelPart rWingLower03;
    private final ModelPart rWingLowerMemebrane;
    private final ModelPart rWing01;
    private final ModelPart rWing02;
    private final ModelPart rWing03;
    private final ModelPart rWingMembrane;

    private boolean realArm = false;
    public BeelzebubEntityModel() {
        super(RenderLayer::getEntityCutoutNoCull, 1, 0, 128, 128);
        chest = new ModelPart(this);
        chest.setPivot(0.0F, -19.5F, 0.0F);
        chest.setTextureOffset(0, 0).addCuboid(-7.5F, -9.0F, -5.5F, 15.0F, 9.0F, 11.0F, 0.0F, false);

        chest2_r1 = new ModelPart(this);
        chest2_r1.setPivot(0.0F, -5.5F, 0.0F);
        chest.addChild(chest2_r1);
        setRotationAngle(chest2_r1, -0.5672F, 0.0F, 0.0F);
        chest2_r1.setTextureOffset(5, 7).addCuboid(-6.5F, 1.0F, -5.5F, 13.0F, 6.0F, 7.0F, 0.0F, false);

        stomach = new ModelPart(this);
        stomach.setPivot(0.0F, -3.0F, 0.0F);
        chest.addChild(stomach);
        stomach.setTextureOffset(0, 40).addCuboid(-6.5F, 0.0F, -4.5F, 13.0F, 18.0F, 9.0F, 0.0F, false);

        fSkirt01 = new ModelPart(this);
        fSkirt01.setPivot(0.0F, 18.3F, -3.7F);
        stomach.addChild(fSkirt01);
        setRotationAngle(fSkirt01, -0.1745F, 0.0F, 0.0F);
        fSkirt01.setTextureOffset(1, 67).addCuboid(-6.5F, -0.1F, -1.0F, 13.0F, 13.0F, 2.0F, 0.0F, false);

        lSkirt01 = new ModelPart(this);
        lSkirt01.setPivot(5.5F, 16.9F, 0.5F);
        stomach.addChild(lSkirt01);
        setRotationAngle(lSkirt01, 0.0F, 0.0F, -0.0873F);
        lSkirt01.setTextureOffset(22, 74).addCuboid(-1.0F, 0.0F, -5.5F, 2.0F, 13.0F, 11.0F, 0.0F, true);

        lSkirt02 = new ModelPart(this);
        lSkirt02.setPivot(-0.1F, 12.8F, 0.0F);
        lSkirt01.addChild(lSkirt02);
        setRotationAngle(lSkirt02, 0.0F, 0.0F, -0.0873F);
        lSkirt02.setTextureOffset(38, 89).addCuboid(-1.0F, 0.0F, -5.5F, 2.0F, 10.0F, 11.0F, 0.0F, false);

        rSkirt01 = new ModelPart(this);
        rSkirt01.setPivot(-5.5F, 16.9F, 0.5F);
        stomach.addChild(rSkirt01);
        setRotationAngle(rSkirt01, 0.0F, 0.0F, 0.0873F);
        rSkirt01.setTextureOffset(22, 74).addCuboid(-1.0F, 0.0F, -5.5F, 2.0F, 13.0F, 11.0F, 0.0F, false);

        rSkirt02 = new ModelPart(this);
        rSkirt02.setPivot(0.1F, 12.8F, 0.0F);
        rSkirt01.addChild(rSkirt02);
        setRotationAngle(rSkirt02, 0.0F, 0.0F, 0.0873F);
        rSkirt02.setTextureOffset(38, 89).addCuboid(-1.0F, 0.0F, -5.5F, 2.0F, 10.0F, 11.0F, 0.0F, true);

        bSkirt01 = new ModelPart(this);
        bSkirt01.setPivot(0.0F, 17.2F, 4.1F);
        stomach.addChild(bSkirt01);
        setRotationAngle(bSkirt01, 0.2269F, 0.0F, 0.0F);
        bSkirt01.setTextureOffset(1, 99).addCuboid(-6.5F, 0.0F, -1.0F, 13.0F, 15.0F, 2.0F, 0.0F, false);

        fSkirt02 = new ModelPart(this);
        fSkirt02.setPivot(0.0F, 14.7F, 0.0F);
        bSkirt01.addChild(fSkirt02);
        setRotationAngle(fSkirt02, -0.2618F, 0.0F, 0.0F);
        fSkirt02.setTextureOffset(0, 116).addCuboid(-6.5F, 0.0F, -1.0F, 13.0F, 10.0F, 2.0F, 0.0F, false);

        lFLeg01 = new ModelPart(this);
        lFLeg01.setPivot(3.7F, 17.6F, -1.9F);
        stomach.addChild(lFLeg01);
        setRotationAngle(lFLeg01, 0.1745F, -0.3491F, 0.0F);
        lFLeg01.setTextureOffset(107, 77).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        lFLeg02 = new ModelPart(this);
        lFLeg02.setPivot(-0.1F, 5.4F, -0.2F);
        lFLeg01.addChild(lFLeg02);
        setRotationAngle(lFLeg02, -0.6981F, 0.0F, -0.0087F);
        lFLeg02.setTextureOffset(108, 89).addCuboid(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, true);

        lFLeg03 = new ModelPart(this);
        lFLeg03.setPivot(-0.1F, 11.2F, 0.2F);
        lFLeg02.addChild(lFLeg03);
        setRotationAngle(lFLeg03, 0.48F, 0.0F, -0.1309F);
        lFLeg03.setTextureOffset(108, 106).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);

        lFLegClaw01 = new ModelPart(this);
        lFLegClaw01.setPivot(-0.2F, 12.7F, -0.5F);
        lFLeg03.addChild(lFLegClaw01);
        setRotationAngle(lFLegClaw01, 0.3491F, 0.1396F, 0.1745F);
        lFLegClaw01.setTextureOffset(117, 108).addCuboid(-0.5F, -0.7F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        lFLegClaw02 = new ModelPart(this);
        lFLegClaw02.setPivot(0.7F, 12.9F, -0.5F);
        lFLeg03.addChild(lFLegClaw02);
        setRotationAngle(lFLegClaw02, 0.3491F, -0.2443F, 0.0524F);
        lFLegClaw02.setTextureOffset(117, 108).addCuboid(-0.5F, -0.7F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        lFLegSpur = new ModelPart(this);
        lFLegSpur.setPivot(0.0F, 4.4F, 0.0F);
        lFLeg03.addChild(lFLegSpur);
        lFLegSpur.setTextureOffset(95, 63).addCuboid(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, true);

        lBLeg01 = new ModelPart(this);
        lBLeg01.setPivot(2.7F, 17.6F, 1.9F);
        stomach.addChild(lBLeg01);
        setRotationAngle(lBLeg01, -0.1745F, 0.3491F, 0.0F);
        lBLeg01.setTextureOffset(107, 77).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        lBLeg02 = new ModelPart(this);
        lBLeg02.setPivot(-0.1F, 5.4F, 0.2F);
        lBLeg01.addChild(lBLeg02);
        setRotationAngle(lBLeg02, 0.6981F, 0.0F, -0.0087F);


        Box_r1 = new ModelPart(this);
        Box_r1.setPivot(-2.6F, 23.5F, -1.1F);
        lBLeg02.addChild(Box_r1);
        setRotationAngle(Box_r1, 0.0F, 3.1416F, 0.0F);
        Box_r1.setTextureOffset(108, 89).addCuboid(-3.9F, -23.5F, -0.4F, 3.0F, 12.0F, 3.0F, 0.0F, true);

        lBLeg03 = new ModelPart(this);
        lBLeg03.setPivot(-0.1F, 11.2F, -0.2F);
        lBLeg02.addChild(lBLeg03);
        setRotationAngle(lBLeg03, -0.48F, 0.0F, -0.1309F);


        Box_r2 = new ModelPart(this);
        Box_r2.setPivot(-2.5F, 12.3F, -0.9F);
        lBLeg03.addChild(Box_r2);
        setRotationAngle(Box_r2, 0.0F, 3.1416F, 0.0F);
        Box_r2.setTextureOffset(108, 106).addCuboid(-3.5F, -12.3F, -0.1F, 2.0F, 14.0F, 2.0F, 0.0F, true);

        lBLegClaw01 = new ModelPart(this);
        lBLegClaw01.setPivot(-0.2F, 12.7F, 0.5F);
        lBLeg03.addChild(lBLegClaw01);
        setRotationAngle(lBLegClaw01, -0.3491F, -0.1396F, 0.1745F);


        Box_r3 = new ModelPart(this);
        Box_r3.setPivot(-2.3F, -0.4F, -1.4F);
        lBLegClaw01.addChild(Box_r3);
        setRotationAngle(Box_r3, 0.0F, 3.1416F, 0.0F);
        Box_r3.setTextureOffset(117, 108).addCuboid(-2.2F, -0.3F, -2.6F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        lBLegClaw02 = new ModelPart(this);
        lBLegClaw02.setPivot(0.7F, 12.9F, 0.5F);
        lBLeg03.addChild(lBLegClaw02);
        setRotationAngle(lBLegClaw02, -0.3491F, 0.2443F, 0.0524F);


        Box_r4 = new ModelPart(this);
        Box_r4.setPivot(-3.2F, -0.6F, -1.4F);
        lBLegClaw02.addChild(Box_r4);
        setRotationAngle(Box_r4, 0.0F, 3.1416F, 0.0F);
        Box_r4.setTextureOffset(117, 108).addCuboid(-4.0F, -0.1F, -2.6F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        lBLegSpur = new ModelPart(this);
        lBLegSpur.setPivot(0.0F, 4.4F, 0.0F);
        lBLeg03.addChild(lBLegSpur);


        Box_r5 = new ModelPart(this);
        Box_r5.setPivot(-2.5F, 7.9F, -0.9F);
        lBLegSpur.addChild(Box_r5);
        setRotationAngle(Box_r5, 0.0F, 3.1416F, 0.0F);
        Box_r5.setTextureOffset(95, 63).addCuboid(-2.5F, -11.9F, 0.9F, 0.0F, 8.0F, 5.0F, 0.0F, true);

        rBLeg01 = new ModelPart(this);
        rBLeg01.setPivot(-2.7F, 17.6F, 1.9F);
        stomach.addChild(rBLeg01);
        setRotationAngle(rBLeg01, -0.1745F, -0.3491F, 0.0F);
        rBLeg01.setTextureOffset(107, 77).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        rBLeg02 = new ModelPart(this);
        rBLeg02.setPivot(0.1F, 5.4F, 0.2F);
        rBLeg01.addChild(rBLeg02);
        setRotationAngle(rBLeg02, 0.6981F, 0.0F, 0.0087F);


        Box_r6 = new ModelPart(this);
        Box_r6.setPivot(2.6F, 23.5F, -1.1F);
        rBLeg02.addChild(Box_r6);
        setRotationAngle(Box_r6, 0.0F, -3.1416F, 0.0F);
        Box_r6.setTextureOffset(108, 89).addCuboid(0.9F, -23.5F, -0.4F, 3.0F, 12.0F, 3.0F, 0.0F, false);

        rBLeg03 = new ModelPart(this);
        rBLeg03.setPivot(0.1F, 11.2F, -0.2F);
        rBLeg02.addChild(rBLeg03);
        setRotationAngle(rBLeg03, -0.48F, 0.0F, 0.1309F);


        Box_r7 = new ModelPart(this);
        Box_r7.setPivot(2.5F, 12.3F, -0.9F);
        rBLeg03.addChild(Box_r7);
        setRotationAngle(Box_r7, 0.0F, -3.1416F, 0.0F);
        Box_r7.setTextureOffset(108, 106).addCuboid(1.5F, -12.3F, -0.1F, 2.0F, 14.0F, 2.0F, 0.0F, false);

        rBLegClaw01 = new ModelPart(this);
        rBLegClaw01.setPivot(0.2F, 12.7F, 0.5F);
        rBLeg03.addChild(rBLegClaw01);
        setRotationAngle(rBLegClaw01, -0.3491F, 0.1396F, -0.1745F);


        Box_r8 = new ModelPart(this);
        Box_r8.setPivot(2.3F, -0.4F, -1.4F);
        rBLegClaw01.addChild(Box_r8);
        setRotationAngle(Box_r8, 0.0F, -3.1416F, 0.0F);
        Box_r8.setTextureOffset(117, 108).addCuboid(1.2F, -0.3F, -2.6F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        rBLegClaw02 = new ModelPart(this);
        rBLegClaw02.setPivot(-0.7F, 12.9F, 0.5F);
        rBLeg03.addChild(rBLegClaw02);
        setRotationAngle(rBLegClaw02, -0.3491F, -0.2443F, -0.0524F);


        Box_r9 = new ModelPart(this);
        Box_r9.setPivot(3.2F, -0.6F, -1.4F);
        rBLegClaw02.addChild(Box_r9);
        setRotationAngle(Box_r9, 0.0F, -3.1416F, 0.0F);
        Box_r9.setTextureOffset(117, 108).addCuboid(3.0F, -0.1F, -2.6F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        rBLegSpur = new ModelPart(this);
        rBLegSpur.setPivot(0.0F, 4.4F, 0.0F);
        rBLeg03.addChild(rBLegSpur);


        Box_r10 = new ModelPart(this);
        Box_r10.setPivot(2.5F, 7.9F, -0.9F);
        rBLegSpur.addChild(Box_r10);
        setRotationAngle(Box_r10, 0.0F, -3.1416F, 0.0F);
        Box_r10.setTextureOffset(95, 63).addCuboid(2.5F, -11.9F, 0.9F, 0.0F, 8.0F, 5.0F, 0.0F, false);

        rFLeg01 = new ModelPart(this);
        rFLeg01.setPivot(-3.7F, 17.6F, -1.9F);
        stomach.addChild(rFLeg01);
        setRotationAngle(rFLeg01, 0.1745F, 0.3491F, 0.0F);
        rFLeg01.setTextureOffset(107, 77).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        rFLeg02 = new ModelPart(this);
        rFLeg02.setPivot(0.1F, 5.4F, -0.2F);
        rFLeg01.addChild(rFLeg02);
        setRotationAngle(rFLeg02, -0.6981F, 0.0F, 0.0087F);
        rFLeg02.setTextureOffset(108, 89).addCuboid(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, false);

        rFLeg03 = new ModelPart(this);
        rFLeg03.setPivot(0.1F, 11.2F, 0.2F);
        rFLeg02.addChild(rFLeg03);
        setRotationAngle(rFLeg03, 0.48F, 0.0F, 0.1309F);
        rFLeg03.setTextureOffset(108, 106).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

        rFLegClaw01 = new ModelPart(this);
        rFLegClaw01.setPivot(0.2F, 12.7F, -0.5F);
        rFLeg03.addChild(rFLegClaw01);
        setRotationAngle(rFLegClaw01, 0.3491F, -0.1396F, -0.1745F);
        rFLegClaw01.setTextureOffset(117, 108).addCuboid(-0.5F, -0.7F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        rFLeg2 = new ModelPart(this);
        rFLeg2.setPivot(-0.7F, 12.9F, -0.5F);
        rFLeg03.addChild(rFLeg2);
        setRotationAngle(rFLeg2, 0.3491F, 0.2443F, -0.0524F);
        rFLeg2.setTextureOffset(117, 108).addCuboid(-0.5F, -0.7F, -3.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        rFLegSpur = new ModelPart(this);
        rFLegSpur.setPivot(0.0F, 4.4F, 0.0F);
        rFLeg03.addChild(rFLegSpur);
        rFLegSpur.setTextureOffset(95, 63).addCuboid(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, -8.9F, 0.0F);
        chest.addChild(head);
        head.setTextureOffset(53, 0).addCuboid(-6.0F, -14.0F, -5.0F, 12.0F, 15.0F, 10.0F, 0.0F, false);

        hoodLTop = new ModelPart(this);
        hoodLTop.setPivot(3.65F, -10.9F, 0.0F);
        head.addChild(hoodLTop);
        setRotationAngle(hoodLTop, 0.0F, 0.0F, 0.3142F);
        hoodLTop.setTextureOffset(42, 29).addCuboid(-5.05F, -4.0F, -5.4F, 6.0F, 2.0F, 12.0F, 0.0F, false);

        hoodRTop = new ModelPart(this);
        hoodRTop.setPivot(-3.65F, -10.9F, 0.0F);
        head.addChild(hoodRTop);
        setRotationAngle(hoodRTop, 0.0F, 0.0F, -0.3142F);
        hoodRTop.setTextureOffset(42, 29).addCuboid(-0.95F, -4.0F, -5.41F, 6.0F, 2.0F, 12.0F, 0.0F, true);

        hoodLSide01 = new ModelPart(this);
        hoodLSide01.setPivot(4.2F, -13.5F, -0.2F);
        head.addChild(hoodLSide01);
        setRotationAngle(hoodLSide01, 0.0F, 0.0F, -0.192F);
        hoodLSide01.setTextureOffset(66, 32).addCuboid(0.0F, -0.3F, -5.29F, 2.0F, 9.0F, 12.0F, 0.0F, false);

        hoodRSide01 = new ModelPart(this);
        hoodRSide01.setPivot(-4.2F, -13.5F, -0.2F);
        head.addChild(hoodRSide01);
        setRotationAngle(hoodRSide01, 0.0F, 0.0F, 0.192F);
        hoodRSide01.setTextureOffset(66, 32).addCuboid(-2.0F, -0.3F, -5.29F, 2.0F, 9.0F, 12.0F, 0.0F, true);

        hoodLSide02 = new ModelPart(this);
        hoodLSide02.setPivot(6.9F, -5.3F, -0.1F);
        head.addChild(hoodLSide02);
        setRotationAngle(hoodLSide02, 0.0F, 0.0F, 0.1309F);
        hoodLSide02.setTextureOffset(89, 23).addCuboid(-1.2F, -0.4F, -5.45F, 2.0F, 8.0F, 12.0F, 0.0F, false);

        hoodRSide02 = new ModelPart(this);
        hoodRSide02.setPivot(-6.9F, -5.3F, -0.1F);
        head.addChild(hoodRSide02);
        setRotationAngle(hoodRSide02, 0.0F, 0.0F, -0.1309F);
        hoodRSide02.setTextureOffset(89, 23).addCuboid(-0.8F, -0.4F, -5.45F, 2.0F, 8.0F, 12.0F, 0.0F, true);

        hoodPipe01 = new ModelPart(this);
        hoodPipe01.setPivot(0.0F, -10.0F, 5.2F);
        head.addChild(hoodPipe01);
        setRotationAngle(hoodPipe01, -0.3142F, -0.3142F, -0.7854F);
        hoodPipe01.setTextureOffset(98, 0).addCuboid(-3.5F, -3.5F, -1.1F, 7.0F, 7.0F, 6.0F, 0.0F, false);

        hoodPipe02 = new ModelPart(this);
        hoodPipe02.setPivot(0.0F, -0.3F, 3.1F);
        hoodPipe01.addChild(hoodPipe02);
        setRotationAngle(hoodPipe02, -0.2182F, -0.2182F, 0.0F);
        hoodPipe02.setTextureOffset(108, 13).addCuboid(-2.5F, -2.5F, 1.0F, 5.0F, 5.0F, 4.0F, 0.0F, false);

        lEye = new ModelPart(this);
        lEye.setPivot(3.4F, -7.7F, -2.8F);
        head.addChild(lEye);
        lEye.setTextureOffset(107, 63).addCuboid(-2.0F, -4.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, false);

        rEye = new ModelPart(this);
        rEye.setPivot(-3.4F, -7.7F, -2.8F);
        head.addChild(rEye);
        rEye.setTextureOffset(107, 63).addCuboid(-3.0F, -4.0F, -2.5F, 5.0F, 7.0F, 5.0F, 0.0F, true);

        hoodBack = new ModelPart(this);
        hoodBack.setPivot(0.0F, -13.7F, 4.5F);
        head.addChild(hoodBack);
        setRotationAngle(hoodBack, 0.1047F, 0.0F, 0.0F);
        hoodBack.setTextureOffset(94, 44).addCuboid(-7.0F, 0.0F, 0.0F, 14.0F, 17.0F, 2.0F, 0.0F, false);

        lAntenna01 = new ModelPart(this);
        lAntenna01.setPivot(2.0F, -12.9F, -4.6F);
        head.addChild(lAntenna01);
        setRotationAngle(lAntenna01, -0.6109F, -0.4363F, 0.1309F);
        lAntenna01.setTextureOffset(43, 0).addCuboid(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 3.0F, 0.0F, true);

        lAntenna02 = new ModelPart(this);
        lAntenna02.setPivot(0.0F, 0.0F, -2.4F);
        lAntenna01.addChild(lAntenna02);
        setRotationAngle(lAntenna02, -0.3142F, 0.0F, 0.0F);
        lAntenna02.setTextureOffset(44, 0).addCuboid(-0.5F, -0.5F, -3.4F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        lAntenna03 = new ModelPart(this);
        lAntenna03.setPivot(0.0F, 0.0F, -3.7F);
        lAntenna02.addChild(lAntenna03);
        setRotationAngle(lAntenna03, -0.3142F, 0.0F, 0.0F);
        lAntenna03.setTextureOffset(43, 0).addCuboid(-0.5F, -0.6F, -3.4F, 1.0F, 1.0F, 4.0F, 0.0F, true);

        lAntenna04 = new ModelPart(this);
        lAntenna04.setPivot(0.0F, -0.2F, -3.7F);
        lAntenna03.addChild(lAntenna04);
        setRotationAngle(lAntenna04, -0.4363F, 0.0F, 0.0F);
        lAntenna04.setTextureOffset(43, 0).addCuboid(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, true);

        lAntenna05 = new ModelPart(this);
        lAntenna05.setPivot(0.0F, -0.2F, -5.6F);
        lAntenna04.addChild(lAntenna05);
        setRotationAngle(lAntenna05, -0.5236F, 0.0F, 0.0F);
        lAntenna05.setTextureOffset(43, 0).addCuboid(-0.5F, -0.5F, -2.6F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        lAntenna06 = new ModelPart(this);
        lAntenna06.setPivot(0.0F, -0.2F, -2.8F);
        lAntenna05.addChild(lAntenna06);
        setRotationAngle(lAntenna06, -0.5236F, 0.0F, 0.0F);
        lAntenna06.setTextureOffset(43, 1).addCuboid(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        rAntenna01 = new ModelPart(this);
        rAntenna01.setPivot(-2.0F, -12.9F, -4.6F);
        head.addChild(rAntenna01);
        setRotationAngle(rAntenna01, -0.6109F, 0.4363F, -0.1309F);
        rAntenna01.setTextureOffset(43, 0).addCuboid(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        rAntenna02 = new ModelPart(this);
        rAntenna02.setPivot(0.0F, 0.0F, -2.4F);
        rAntenna01.addChild(rAntenna02);
        setRotationAngle(rAntenna02, -0.3142F, 0.0F, 0.0F);
        rAntenna02.setTextureOffset(44, 0).addCuboid(-0.5F, -0.5F, -3.4F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        rAntenna03 = new ModelPart(this);
        rAntenna03.setPivot(0.0F, 0.0F, -3.7F);
        rAntenna02.addChild(rAntenna03);
        setRotationAngle(rAntenna03, -0.3142F, 0.0F, 0.0F);
        rAntenna03.setTextureOffset(43, 0).addCuboid(-0.5F, -0.6F, -3.4F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        rAntenna04 = new ModelPart(this);
        rAntenna04.setPivot(0.0F, -0.2F, -3.7F);
        rAntenna03.addChild(rAntenna04);
        setRotationAngle(rAntenna04, -0.4363F, 0.0F, 0.0F);
        rAntenna04.setTextureOffset(43, 0).addCuboid(-0.5F, -0.5F, -5.5F, 1.0F, 1.0F, 6.0F, 0.0F, false);

        rAntenna05 = new ModelPart(this);
        rAntenna05.setPivot(0.0F, -0.2F, -5.6F);
        rAntenna04.addChild(rAntenna05);
        setRotationAngle(rAntenna05, -0.5236F, 0.0F, 0.0F);
        rAntenna05.setTextureOffset(43, 0).addCuboid(-0.5F, -0.5F, -2.6F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        rAntenna06 = new ModelPart(this);
        rAntenna06.setPivot(0.0F, -0.2F, -2.8F);
        rAntenna05.addChild(rAntenna06);
        setRotationAngle(rAntenna06, -0.5236F, 0.0F, 0.0F);
        rAntenna06.setTextureOffset(43, 1).addCuboid(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        chestCloth = new ModelPart(this);
        chestCloth.setPivot(0.0F, 0.0F, 0.0F);
        chest.addChild(chestCloth);
        chestCloth.setTextureOffset(0, 21).addCuboid(-7.5F, 0.0F, -5.5F, 15.0F, 6.0F, 11.0F, 0.0F, false);

        lArm01 = new ModelPart(this);
        lArm01.setPivot(6.8F, -8.0F, 0.0F);
        chest.addChild(lArm01);
        setRotationAngle(lArm01, 0.0F, 0.0F, -0.2793F);
        lArm01.setTextureOffset(45, 44).addCuboid(-2.5F, -0.5F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

        lArm02 = new ModelPart(this);
        lArm02.setPivot(0.4F, 8.8F, 2.3F);
        lArm01.addChild(lArm02);
        setRotationAngle(lArm02, 0.0F, 0.0F, 0.2269F);
        lArm02.setTextureOffset(42, 62).addCuboid(-2.5F, 0.0F, -4.7F, 5.0F, 12.0F, 5.0F, 0.0F, false);

        lSleeve01 = new ModelPart(this);
        lSleeve01.setPivot(0.0F, 10.5F, -0.4F);
        lArm02.addChild(lSleeve01);
        setRotationAngle(lSleeve01, -0.6981F, 0.0F, 0.0F);
        lSleeve01.setTextureOffset(61, 54).addCuboid(-2.4F, -1.5F, 0.2F, 5.0F, 3.0F, 5.0F, 0.0F, false);

        lSleeve02 = new ModelPart(this);
        lSleeve02.setPivot(0.0F, -0.2F, 0.9F);
        lSleeve01.addChild(lSleeve02);
        setRotationAngle(lSleeve02, -0.7854F, 0.0F, 0.0F);
        lSleeve02.setTextureOffset(66, 67).addCuboid(-2.0F, -3.4F, -4.5F, 4.0F, 4.0F, 6.0F, 0.0F, false);

        lArm03 = new ModelPart(this);
        lArm03.setPivot(0.0F, 11.8F, -1.9F);
        lArm02.addChild(lArm03);
        lArm03.setTextureOffset(0, 82).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 13.0F, 3.0F, 0.0F, false);

        lClaw01 = new ModelPart(this);
        lClaw01.setPivot(0.6F, 12.4F, -1.1F);
        lArm03.addChild(lClaw01);
        setRotationAngle(lClaw01, 0.2618F, -0.829F, 0.1309F);
        lClaw01.setTextureOffset(0, 0).addCuboid(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        lClaw02 = new ModelPart(this);
        lClaw02.setPivot(0.6F, 12.4F, 0.7F);
        lArm03.addChild(lClaw02);
        setRotationAngle(lClaw02, 0.2618F, -1.7453F, 0.0F);
        lClaw02.setTextureOffset(0, 0).addCuboid(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        rArm01 = new ModelPart(this);
        rArm01.setPivot(-6.8F, -8.0F, 0.0F);
        chest.addChild(rArm01);
        setRotationAngle(rArm01, 0.0F, 0.0F, 0.2793F);
        rArm01.setTextureOffset(45, 44).addCuboid(-2.5F, -0.5F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, true);

        rArm02 = new ModelPart(this);
        rArm02.setPivot(-0.4F, 8.8F, 2.3F);
        rArm01.addChild(rArm02);
        setRotationAngle(rArm02, 0.0F, 0.0F, -0.2269F);
        rArm02.setTextureOffset(42, 62).addCuboid(-2.5F, 0.0F, -4.7F, 5.0F, 12.0F, 5.0F, 0.0F, true);

        rSleeve01 = new ModelPart(this);
        rSleeve01.setPivot(0.0F, 10.5F, -0.4F);
        rArm02.addChild(rSleeve01);
        setRotationAngle(rSleeve01, -0.6981F, 0.0F, 0.0F);
        rSleeve01.setTextureOffset(61, 54).addCuboid(-2.6F, -1.5F, 0.2F, 5.0F, 3.0F, 5.0F, 0.0F, true);

        rSleeve02 = new ModelPart(this);
        rSleeve02.setPivot(0.0F, -0.2F, 0.9F);
        rSleeve01.addChild(rSleeve02);
        setRotationAngle(rSleeve02, -0.7854F, 0.0F, 0.0F);
        rSleeve02.setTextureOffset(66, 67).addCuboid(-2.0F, -3.4F, -4.5F, 4.0F, 4.0F, 6.0F, 0.0F, true);

        rArm03 = new ModelPart(this);
        rArm03.setPivot(0.0F, 11.8F, -1.9F);
        rArm02.addChild(rArm03);
        rArm03.setTextureOffset(0, 82).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 13.0F, 3.0F, 0.0F, true);

        rClaw01 = new ModelPart(this);
        rClaw01.setPivot(-0.6F, 12.4F, -1.1F);
        rArm03.addChild(rClaw01);
        setRotationAngle(rClaw01, 0.2618F, 0.829F, -0.1309F);
        rClaw01.setTextureOffset(0, 0).addCuboid(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

        rClaw02 = new ModelPart(this);
        rClaw02.setPivot(-0.6F, 12.4F, 0.7F);
        rArm03.addChild(rClaw02);
        setRotationAngle(rClaw02, 0.2618F, 1.7453F, 0.0F);
        rClaw02.setTextureOffset(0, 0).addCuboid(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

        lWing01 = new ModelPart(this);
        lWing01.setPivot(4.6F, -5.4F, 4.9F);
        chest.addChild(lWing01);
        setRotationAngle(lWing01, 0.1047F, 0.0349F, -0.6545F);
        lWing01.setTextureOffset(34, 111).addCuboid(-1.0F, -1.4F, 0.0F, 3.0F, 11.0F, 2.0F, 0.0F, true);

        lWing02 = new ModelPart(this);
        lWing02.setPivot(0.8F, 9.2F, 1.0F);
        lWing01.addChild(lWing02);
        setRotationAngle(lWing02, 0.0F, 0.0F, 0.0873F);
        lWing02.setTextureOffset(45, 111).addCuboid(-1.0F, 0.1F, -0.5F, 2.0F, 15.0F, 1.0F, 0.0F, true);

        lWing03 = new ModelPart(this);
        lWing03.setPivot(0.0F, 14.6F, 0.0F);
        lWing02.addChild(lWing03);
        setRotationAngle(lWing03, 0.0F, 0.0F, 0.1484F);
        lWing03.setTextureOffset(54, 111).addCuboid(-1.0F, 0.1F, -0.49F, 2.0F, 15.0F, 1.0F, 0.0F, true);

        lWingMembrane = new ModelPart(this);
        lWingMembrane.setPivot(-0.8F, 0.0F, 0.5F);
        lWing01.addChild(lWingMembrane);
        setRotationAngle(lWingMembrane, 0.0F, 0.0F, 0.1047F);
        lWingMembrane.setTextureOffset(66, 82).addCuboid(-17.9F, -1.5F, 0.6F, 20.0F, 45.0F, 0.0F, 0.0F, true);

        lWingLower01 = new ModelPart(this);
        lWingLower01.setPivot(4.6F, -2.4F, 4.4F);
        chest.addChild(lWingLower01);
        setRotationAngle(lWingLower01, 0.1047F, 0.0349F, -0.3927F);
        lWingLower01.setTextureOffset(34, 111).addCuboid(-1.0F, -1.4F, 0.0F, 3.0F, 11.0F, 2.0F, 0.0F, true);

        lWingLower02 = new ModelPart(this);
        lWingLower02.setPivot(0.8F, 9.2F, 1.0F);
        lWingLower01.addChild(lWingLower02);
        setRotationAngle(lWingLower02, 0.0F, 0.0F, 0.0873F);
        lWingLower02.setTextureOffset(45, 111).addCuboid(-1.0F, 0.1F, -0.5F, 2.0F, 15.0F, 1.0F, 0.0F, true);

        lWingLower03 = new ModelPart(this);
        lWingLower03.setPivot(0.0F, 14.6F, 0.0F);
        lWingLower02.addChild(lWingLower03);
        setRotationAngle(lWingLower03, 0.0F, 0.0F, 0.1484F);
        lWingLower03.setTextureOffset(54, 111).addCuboid(-1.0F, 0.1F, -0.49F, 2.0F, 15.0F, 1.0F, 0.0F, true);

        lWingLowerMemebrane = new ModelPart(this);
        lWingLowerMemebrane.setPivot(-0.8F, 0.0F, 0.5F);
        lWingLower01.addChild(lWingLowerMemebrane);
        setRotationAngle(lWingLowerMemebrane, 0.0F, 0.0F, 0.1047F);
        lWingLowerMemebrane.setTextureOffset(66, 82).addCuboid(-17.9F, -1.5F, 0.6F, 20.0F, 45.0F, 0.0F, 0.0F, true);

        rWingLower01 = new ModelPart(this);
        rWingLower01.setPivot(-4.6F, -2.4F, 4.4F);
        chest.addChild(rWingLower01);
        setRotationAngle(rWingLower01, 0.1047F, -0.0349F, 0.3927F);
        rWingLower01.setTextureOffset(34, 111).addCuboid(-2.0F, -1.4F, 0.0F, 3.0F, 11.0F, 2.0F, 0.0F, false);

        rWingLower02 = new ModelPart(this);
        rWingLower02.setPivot(-0.8F, 9.2F, 1.0F);
        rWingLower01.addChild(rWingLower02);
        setRotationAngle(rWingLower02, 0.0F, 0.0F, -0.0873F);
        rWingLower02.setTextureOffset(45, 111).addCuboid(-1.0F, 0.1F, -0.5F, 2.0F, 15.0F, 1.0F, 0.0F, false);

        rWingLower03 = new ModelPart(this);
        rWingLower03.setPivot(0.0F, 14.6F, 0.0F);
        rWingLower02.addChild(rWingLower03);
        setRotationAngle(rWingLower03, 0.0F, 0.0F, -0.1484F);
        rWingLower03.setTextureOffset(54, 111).addCuboid(-1.0F, 0.1F, -0.49F, 2.0F, 15.0F, 1.0F, 0.0F, false);

        rWingLowerMemebrane = new ModelPart(this);
        rWingLowerMemebrane.setPivot(0.8F, 0.0F, 0.5F);
        rWingLower01.addChild(rWingLowerMemebrane);
        setRotationAngle(rWingLowerMemebrane, 0.0F, 0.0F, -0.1047F);
        rWingLowerMemebrane.setTextureOffset(66, 82).addCuboid(-2.1F, -1.5F, 0.6F, 20.0F, 45.0F, 0.0F, 0.0F, false);

        rWing01 = new ModelPart(this);
        rWing01.setPivot(-4.6F, -5.4F, 4.9F);
        chest.addChild(rWing01);
        setRotationAngle(rWing01, 0.1047F, -0.0349F, 0.6545F);
        rWing01.setTextureOffset(34, 111).addCuboid(-2.0F, -1.4F, 0.0F, 3.0F, 11.0F, 2.0F, 0.0F, false);

        rWing02 = new ModelPart(this);
        rWing02.setPivot(-0.8F, 9.2F, 1.0F);
        rWing01.addChild(rWing02);
        setRotationAngle(rWing02, 0.0F, 0.0F, -0.0873F);
        rWing02.setTextureOffset(45, 111).addCuboid(-1.0F, 0.1F, -0.5F, 2.0F, 15.0F, 1.0F, 0.0F, false);

        rWing03 = new ModelPart(this);
        rWing03.setPivot(0.0F, 14.6F, 0.0F);
        rWing02.addChild(rWing03);
        setRotationAngle(rWing03, 0.0F, 0.0F, -0.1484F);
        rWing03.setTextureOffset(54, 111).addCuboid(-1.0F, 0.1F, -0.49F, 2.0F, 15.0F, 1.0F, 0.0F, false);

        rWingMembrane = new ModelPart(this);
        rWingMembrane.setPivot(0.8F, 0.0F, 0.5F);
        rWing01.addChild(rWingMembrane);
        setRotationAngle(rWingMembrane, 0.0F, 0.0F, -0.1047F);
        rWingMembrane.setTextureOffset(66, 82).addCuboid(-2.1F, -1.5F, 0.6F, 20.0F, 45.0F, 0.0F, 0.0F, false);
    }
    @Override
    public void setAngles(BeelzebubEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        realArm = false;
        super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        realArm = true;
        head.setPivot(0.0F, -8.9F, 0.0F);
        copyRotation(lArm01, super.leftArm);
        lArm01.setPivot(6.8F, -8.0F, 0.0F);
        lArm01.pitch /= 3;
        lArm01.pitch -= 0.1745f;
        lArm01.yaw -= 0.0873f;
        lArm01.roll -= 0.2356f;
        copyRotation(rArm01, super.rightArm);
        rArm01.setPivot(-6.8F, -8.0F, 0.0F);
        rArm01.pitch /= 3;
        rArm01.pitch -= 0.1745f;
        rArm01.yaw += 0.0873f;
        rArm01.roll += 0.2356f;

        this.rBLeg01.pitch = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
        this.lBLeg01.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
        this.rFLeg01.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 0.3F * limbSwingAmount;
        this.lFLeg01.pitch = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
        if(entity.hurtTime > 0){
            lWing01.pitch = 0.0873f + (1 + MathHelper.sin(ageInTicks)) / 8;
            rWing01.pitch = 0.0873f + (1 + MathHelper.sin(ageInTicks)) / 8;
            lWingLower01.yaw = 0.0349F + (1 + MathHelper.sin(ageInTicks)) / 8;
            rWingLower01.yaw = -0.0349F - (1 + MathHelper.sin(ageInTicks)) / 8;
        }else{
            lWing01.pitch = 0.0873f + (1 + MathHelper.sin(ageInTicks / 12)) / 8; //adjust values
            rWing01.pitch = 0.0873f + (1 + MathHelper.sin(ageInTicks / 12)) / 8;
            lWingLower01.yaw = 0.0349F + (1 + MathHelper.sin(ageInTicks / 12)) / 8;
            rWingLower01.yaw = -0.0349F - (1 + MathHelper.sin(ageInTicks / 12)) / 8;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        chest.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    protected ModelPart getArm(Arm arm) {
        return this.realArm ? (arm == Arm.LEFT ? this.lArm01 : this.rArm01) : super.getArm(arm);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
    private void copyRotation(ModelPart to, ModelPart from) {
        to.pitch = from.pitch;
        to.yaw = from.yaw;
        to.roll = from.roll;
    }
}