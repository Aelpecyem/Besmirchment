// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

package de.aelpecyem.besmirchment.client.model;

import de.aelpecyem.besmirchment.common.entity.WerepyreEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class WerepyreEntityModel<T extends WerepyreEntity> extends BipedEntityModel<WerepyreEntity> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart stomach;
    private final ModelPart tail01;
    private final ModelPart tail02;
    private final ModelPart tail03;
    private final ModelPart tail04;
    private final ModelPart fur06;
    private final ModelPart fur05;
    private final ModelPart fur04;
    private final ModelPart fur03;
    private final ModelPart fur02;
    private final ModelPart lWing01;
    private final ModelPart lWing02;
    private final ModelPart lWing03;
    private final ModelPart lWing04;
    private final ModelPart lWing05;
    private final ModelPart lWingMembrane03;
    private final ModelPart lWingMembrane02;
    private final ModelPart lWingFur2;
    private final ModelPart lWingMembrane01;
    private final ModelPart lWingFur01;
    private final ModelPart rWing01;
    private final ModelPart rWing02;
    private final ModelPart rWing03;
    private final ModelPart rWing04;
    private final ModelPart rWing05;
    private final ModelPart rWingMembrane01;
    private final ModelPart rWingMembrane02;
    private final ModelPart rWingFur02;
    private final ModelPart rWingMembrane03;
    private final ModelPart rWingFur01;
    private final ModelPart BipedLeftLeg;
    private final ModelPart lLeg02;
    private final ModelPart lLeg03;
    private final ModelPart lFoot;
    private final ModelPart lFootClaw01;
    private final ModelPart lFootClaw02;
    private final ModelPart lFootClaw03;
    private final ModelPart BipedRightLeg;
    private final ModelPart rLeg02;
    private final ModelPart rLeg03;
    private final ModelPart rFoot;
    private final ModelPart rFootClaw01;
    private final ModelPart rFootClaw02;
    private final ModelPart rFootClaw03;
    private final ModelPart neck;
    private final ModelPart head2;
    private final ModelPart jawUpperLeft;
    private final ModelPart upperTeethLeft01;
    private final ModelPart upperTeethLeft02;
    private final ModelPart jawUpperRight;
    private final ModelPart upperTeethRight;
    private final ModelPart jawLower;
    private final ModelPart lowerTeeth01;
    private final ModelPart lowerTeeth02;
    private final ModelPart lEar01;
    private final ModelPart lEar02;
    private final ModelPart rEar01;
    private final ModelPart rEar02;
    private final ModelPart lCheekFur;
    private final ModelPart rCheekFur;
    private final ModelPart snout;
    private final ModelPart fur01;
    private final ModelPart lArm01;
    private final ModelPart lArm02;
    private final ModelPart lArmFur;
    private final ModelPart lClawJoint;
    private final ModelPart lClaw01;
    private final ModelPart lClaw02;
    private final ModelPart lClaw03;
    private final ModelPart leftHandItem;
    private final ModelPart rArm01;
    private final ModelPart rArm02;
    private final ModelPart rArmFur;
    private final ModelPart rClawJoint;
    private final ModelPart rClaw01;
    private final ModelPart rClaw02;
    private final ModelPart rClaw03;
    private final ModelPart rightHandItem;

    public WerepyreEntityModel() {
        super(1.0F, 0.0F, 128, 128);
        body = new ModelPart(this);
        body.setPivot(0.0F, -12.7F, 0.0F);
        setRotationAngle(body, 0.5672F, 0.0F, 0.0F);
        body.setTextureOffset(49, 15).addCuboid(-5.5F, -3.3F, -2.0F, 11.0F, 11.0F, 8.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, -2.0F, 2.0F);
        body.addChild(head);
        head.setTextureOffset(1, 2).addCuboid(-0.5F, 0.7F, -0.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        stomach = new ModelPart(this);
        stomach.setPivot(0.0F, 6.9F, 2.6F);
        body.addChild(stomach);
        setRotationAngle(stomach, -0.3927F, 0.0F, 0.0F);
        stomach.setTextureOffset(16, 16).addCuboid(-5.0F, 0.0F, -3.0F, 10.0F, 12.0F, 6.0F, 0.0F, false);

        tail01 = new ModelPart(this);
        tail01.setPivot(0.0F, 8.6F, 2.0F);
        stomach.addChild(tail01);
        setRotationAngle(tail01, 0.1047F, 0.0F, 0.0F);
        tail01.setTextureOffset(112, 18).addCuboid(-1.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        tail02 = new ModelPart(this);
        tail02.setPivot(0.0F, 3.7F, 0.0F);
        tail01.addChild(tail02);
        setRotationAngle(tail02, -0.2094F, 0.0F, 0.0F);
        tail02.setTextureOffset(115, 27).addCuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        tail03 = new ModelPart(this);
        tail03.setPivot(0.0F, 6.5F, 0.1F);
        tail02.addChild(tail03);
        setRotationAngle(tail03, 0.1396F, 0.0F, 0.0F);
        tail03.setTextureOffset(112, 40).addCuboid(-1.5F, 0.2F, -1.5F, 3.0F, 4.0F, 3.0F, -0.2F, false);

        tail04 = new ModelPart(this);
        tail04.setPivot(0.0F, 0.0F, 0.4F);
        tail03.addChild(tail04);
        setRotationAngle(tail04, 0.0698F, 0.0F, 0.0F);
        tail04.setTextureOffset(116, 7).addCuboid(-1.0F, 0.0F, -2.0F, 2.0F, 7.0F, 2.0F, -0.1F, false);

        fur06 = new ModelPart(this);
        fur06.setPivot(0.0F, 6.6F, 1.5F);
        stomach.addChild(fur06);
        setRotationAngle(fur06, 0.6981F, 0.0F, 0.0F);
        fur06.setTextureOffset(90, 52).addCuboid(-2.0F, -1.0F, 0.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

        fur05 = new ModelPart(this);
        fur05.setPivot(0.0F, 4.7F, 1.6F);
        stomach.addChild(fur05);
        setRotationAngle(fur05, 0.6981F, 0.0F, 0.0F);
        fur05.setTextureOffset(90, 43).addCuboid(-2.0F, -1.0F, 0.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);

        fur04 = new ModelPart(this);
        fur04.setPivot(0.0F, -0.3F, 4.9F);
        body.addChild(fur04);
        setRotationAngle(fur04, 0.4363F, 0.0F, 0.0F);
        fur04.setTextureOffset(90, 35).addCuboid(-2.5F, -1.0F, 0.0F, 5.0F, 4.0F, 2.0F, 0.0F, false);

        fur03 = new ModelPart(this);
        fur03.setPivot(0.0F, -3.6F, 4.5F);
        body.addChild(fur03);
        setRotationAngle(fur03, 0.6807F, 0.0F, 0.0F);
        fur03.setTextureOffset(90, 24).addCuboid(-4.0F, -1.0F, 0.0F, 8.0F, 7.0F, 2.0F, 0.0F, false);

        fur02 = new ModelPart(this);
        fur02.setPivot(0.0F, -4.7F, 3.4F);
        body.addChild(fur02);
        setRotationAngle(fur02, 1.0908F, 0.0F, 0.0F);
        fur02.setTextureOffset(90, 11).addCuboid(-5.0F, -1.0F, 0.0F, 10.0F, 8.0F, 2.0F, 0.0F, false);

        lWing01 = new ModelPart(this);
        lWing01.setPivot(2.5F, 1.2F, 6.4F);
        body.addChild(lWing01);
        setRotationAngle(lWing01, -0.2618F, 0.5236F, -0.2618F);
        lWing01.setTextureOffset(78, 99).addCuboid(-1.7F, -0.5F, -1.1F, 2.0F, 4.0F, 9.0F, 0.0F, false);

        lWing02 = new ModelPart(this);
        lWing02.setPivot(0.346F, 0.3487F, 9.1164F);
        lWing01.addChild(lWing02);
        setRotationAngle(lWing02, 1.3526F, 0.0F, 0.0F);
        lWing02.setTextureOffset(93, 114).addCuboid(-1.7243F, -1.5568F, -1.8828F, 2.0F, 3.0F, 11.0F, 0.0F, false);

        lWing03 = new ModelPart(this);
        lWing03.setPivot(0.1F, -0.5F, 9.1F);
        lWing02.addChild(lWing03);
        setRotationAngle(lWing03, -0.1222F, 0.0F, 0.0F);
        lWing03.setTextureOffset(108, 99).addCuboid(-1.7F, -0.7F, -1.9F, 2.0F, 13.0F, 3.0F, 0.0F, false);

        lWing04 = new ModelPart(this);
        lWing04.setPivot(0.0F, 11.7F, 0.0F);
        lWing03.addChild(lWing04);
        setRotationAngle(lWing04, -1.0472F, 0.0F, 0.0F);
        lWing04.setTextureOffset(118, 99).addCuboid(-1.2F, -0.4F, -0.9F, 1.0F, 17.0F, 2.0F, 0.0F, false);

        lWing05 = new ModelPart(this);
        lWing05.setPivot(-0.7F, 17.0F, 0.0F);
        lWing04.addChild(lWing05);
        setRotationAngle(lWing05, -0.6981F, 0.0F, 0.0F);
        lWing05.setTextureOffset(124, 99).addCuboid(-0.3F, -1.0F, -1.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);

        lWingMembrane03 = new ModelPart(this);
        lWingMembrane03.setPivot(0.0F, 7.7F, -0.4F);
        lWing04.addChild(lWingMembrane03);
        lWingMembrane03.setTextureOffset(0, 62).addCuboid(-0.6F, -9.6F, -29.5F, 0.0F, 37.0F, 29.0F, 0.0F, false);

        lWingMembrane02 = new ModelPart(this);
        lWingMembrane02.setPivot(-0.046F, 0.5513F, 7.4836F);
        lWing02.addChild(lWingMembrane02);
        setRotationAngle(lWingMembrane02, -1.5795F, 0.0F, 0.0F);
        lWingMembrane02.setTextureOffset(54, 38).addCuboid(-0.554F, -2.2513F, -13.2836F, 0.0F, 30.0F, 25.0F, 0.0F, false);

        lWingFur2 = new ModelPart(this);
        lWingFur2.setPivot(-2.1F, 0.3F, 2.7F);
        lWing02.addChild(lWingFur2);
        setRotationAngle(lWingFur2, 0.0F, -1.5272F, 0.2182F);
        lWingFur2.setTextureOffset(90, 24).addCuboid(-4.5F, 0.0F, -2.0F, 8.0F, 7.0F, 2.0F, 0.0F, false);

        lWingMembrane01 = new ModelPart(this);
        lWingMembrane01.setPivot(0.0F, 5.9F, -5.0F);
        lWing01.addChild(lWingMembrane01);
        setRotationAngle(lWingMembrane01, -0.3491F, 0.0F, 0.0F);
        lWingMembrane01.setTextureOffset(0, 50).addCuboid(-0.7F, -9.2F, 0.6F, 0.0F, 22.0F, 16.0F, 0.0F, false);

        lWingFur01 = new ModelPart(this);
        lWingFur01.setPivot(-2.0F, 1.5F, 7.1F);
        lWing01.addChild(lWingFur01);
        setRotationAngle(lWingFur01, 0.0F, -1.5272F, 0.2182F);
        lWingFur01.setTextureOffset(90, 11).addCuboid(-7.5F, 1.0F, -2.0F, 10.0F, 8.0F, 2.0F, 0.0F, false);

        rWing01 = new ModelPart(this);
        rWing01.setPivot(-2.5F, 1.2F, 6.4F);
        body.addChild(rWing01);
        setRotationAngle(rWing01, -0.2618F, -0.5236F, 0.2618F);
        rWing01.setTextureOffset(78, 99).addCuboid(-0.3F, -0.5F, -1.1F, 2.0F, 4.0F, 9.0F, 0.0F, true);

        rWing02 = new ModelPart(this);
        rWing02.setPivot(-0.346F, 0.3487F, 9.1164F);
        rWing01.addChild(rWing02);
        setRotationAngle(rWing02, 1.3526F, 0.0F, 0.0F);
        rWing02.setTextureOffset(93, 114).addCuboid(-0.2757F, -1.5568F, -1.8828F, 2.0F, 3.0F, 11.0F, 0.0F, true);

        rWing03 = new ModelPart(this);
        rWing03.setPivot(-0.1F, -0.5F, 9.1F);
        rWing02.addChild(rWing03);
        setRotationAngle(rWing03, -0.1222F, 0.0F, 0.0F);
        rWing03.setTextureOffset(108, 99).addCuboid(-0.3F, -0.7F, -1.9F, 2.0F, 13.0F, 3.0F, 0.0F, true);

        rWing04 = new ModelPart(this);
        rWing04.setPivot(0.0F, 11.7F, 0.0F);
        rWing03.addChild(rWing04);
        setRotationAngle(rWing04, -1.0472F, 0.0F, 0.0F);
        rWing04.setTextureOffset(118, 99).addCuboid(0.2F, -0.4F, -0.9F, 1.0F, 17.0F, 2.0F, 0.0F, true);

        rWing05 = new ModelPart(this);
        rWing05.setPivot(0.7F, 17.0F, 0.0F);
        rWing04.addChild(rWing05);
        setRotationAngle(rWing05, -0.6981F, 0.0F, 0.0F);
        rWing05.setTextureOffset(124, 99).addCuboid(-0.7F, -1.0F, -1.0F, 1.0F, 15.0F, 1.0F, 0.0F, true);

        rWingMembrane01 = new ModelPart(this);
        rWingMembrane01.setPivot(0.0F, 7.7F, -0.4F);
        rWing04.addChild(rWingMembrane01);
        rWingMembrane01.setTextureOffset(0, 62).addCuboid(0.6F, -9.6F, -29.5F, 0.0F, 37.0F, 29.0F, 0.0F, true);

        rWingMembrane02 = new ModelPart(this);
        rWingMembrane02.setPivot(0.046F, 0.5513F, 7.4836F);
        rWing02.addChild(rWingMembrane02);
        setRotationAngle(rWingMembrane02, -1.5795F, 0.0F, 0.0F);
        rWingMembrane02.setTextureOffset(54, 38).addCuboid(0.554F, -2.2513F, -13.2836F, 0.0F, 30.0F, 25.0F, 0.0F, true);

        rWingFur02 = new ModelPart(this);
        rWingFur02.setPivot(2.1F, 0.3F, 2.7F);
        rWing02.addChild(rWingFur02);
        setRotationAngle(rWingFur02, 0.0F, 1.5272F, -0.2182F);
        rWingFur02.setTextureOffset(90, 24).addCuboid(-3.5F, 0.0F, -2.0F, 8.0F, 7.0F, 2.0F, 0.0F, true);

        rWingMembrane03 = new ModelPart(this);
        rWingMembrane03.setPivot(0.0F, 5.9F, -5.0F);
        rWing01.addChild(rWingMembrane03);
        setRotationAngle(rWingMembrane03, -0.3491F, 0.0F, 0.0F);
        rWingMembrane03.setTextureOffset(0, 50).addCuboid(0.7F, -9.2F, 0.6F, 0.0F, 22.0F, 16.0F, 0.0F, true);

        rWingFur01 = new ModelPart(this);
        rWingFur01.setPivot(2.0F, 1.5F, 7.1F);
        rWing01.addChild(rWingFur01);
        setRotationAngle(rWingFur01, 0.0F, 1.5272F, -0.2182F);
        rWingFur01.setTextureOffset(90, 11).addCuboid(-2.5F, 1.0F, -2.0F, 10.0F, 8.0F, 2.0F, 0.0F, true);

        BipedLeftLeg = new ModelPart(this);
        BipedLeftLeg.setPivot(2.7F, 1.4F, 7.2F);
        setRotationAngle(BipedLeftLeg, -0.6109F, -0.2269F, -0.0873F);
        BipedLeftLeg.setTextureOffset(11, 46).addCuboid(-2.3F, -1.1F, -1.9F, 5.0F, 13.0F, 5.0F, 0.0F, false);

        lLeg02 = new ModelPart(this);
        lLeg02.setPivot(0.0F, 9.8F, 0.5F);
        BipedLeftLeg.addChild(lLeg02);
        setRotationAngle(lLeg02, 1.309F, -0.0524F, 0.0F);
        lLeg02.setTextureOffset(0, 35).addCuboid(-2.01F, 0.4F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        lLeg03 = new ModelPart(this);
        lLeg03.setPivot(0.0F, 5.9F, 0.8F);
        lLeg02.addChild(lLeg03);
        setRotationAngle(lLeg03, -0.7854F, 0.0F, 0.0873F);
        lLeg03.setTextureOffset(0, 22).addCuboid(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);

        lFoot = new ModelPart(this);
        lFoot.setPivot(0.0F, 8.7F, -1.2F);
        lLeg03.addChild(lFoot);
        setRotationAngle(lFoot, 0.1309F, 0.0436F, -0.0087F);
        lFoot.setTextureOffset(0, 14).addCuboid(-2.0F, 0.0F, -2.8F, 4.0F, 2.0F, 5.0F, 0.0F, false);

        lFootClaw01 = new ModelPart(this);
        lFootClaw01.setPivot(-1.3F, 0.5F, -2.6F);
        lFoot.addChild(lFootClaw01);
        setRotationAngle(lFootClaw01, 0.2269F, 0.3229F, 0.0F);
        lFootClaw01.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        lFootClaw02 = new ModelPart(this);
        lFootClaw02.setPivot(0.0F, 0.5F, -2.6F);
        lFoot.addChild(lFootClaw02);
        setRotationAngle(lFootClaw02, 0.2269F, 0.0F, 0.0F);
        lFootClaw02.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        lFootClaw03 = new ModelPart(this);
        lFootClaw03.setPivot(1.3F, 0.5F, -2.6F);
        lFoot.addChild(lFootClaw03);
        setRotationAngle(lFootClaw03, 0.2269F, -0.3229F, 0.0F);
        lFootClaw03.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, false);

        BipedRightLeg = new ModelPart(this);
        BipedRightLeg.setPivot(-2.7F, 1.4F, 7.2F);
        setRotationAngle(BipedRightLeg, -0.6109F, 0.2269F, 0.0873F);
        BipedRightLeg.setTextureOffset(11, 46).addCuboid(-2.7F, -1.1F, -1.9F, 5.0F, 13.0F, 5.0F, 0.0F, true);

        rLeg02 = new ModelPart(this);
        rLeg02.setPivot(0.0F, 9.8F, 0.5F);
        BipedRightLeg.addChild(rLeg02);
        setRotationAngle(rLeg02, 1.309F, 0.0524F, 0.0F);
        rLeg02.setTextureOffset(0, 35).addCuboid(-1.99F, 0.4F, -2.0F, 4.0F, 7.0F, 4.0F, 0.0F, true);

        rLeg03 = new ModelPart(this);
        rLeg03.setPivot(0.0F, 5.9F, 0.8F);
        rLeg02.addChild(rLeg03);
        setRotationAngle(rLeg03, -0.7854F, 0.0F, -0.0873F);
        rLeg03.setTextureOffset(0, 22).addCuboid(-1.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, true);

        rFoot = new ModelPart(this);
        rFoot.setPivot(0.0F, 8.7F, -1.2F);
        rLeg03.addChild(rFoot);
        setRotationAngle(rFoot, 0.1309F, -0.0436F, 0.0087F);
        rFoot.setTextureOffset(0, 14).addCuboid(-2.0F, 0.0F, -2.8F, 4.0F, 2.0F, 5.0F, 0.0F, true);

        rFootClaw01 = new ModelPart(this);
        rFootClaw01.setPivot(1.3F, 0.5F, -2.6F);
        rFoot.addChild(rFootClaw01);
        setRotationAngle(rFootClaw01, 0.2269F, -0.3229F, 0.0F);
        rFootClaw01.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        rFootClaw02 = new ModelPart(this);
        rFootClaw02.setPivot(0.0F, 0.5F, -2.6F);
        rFoot.addChild(rFootClaw02);
        setRotationAngle(rFootClaw02, 0.2269F, 0.0F, 0.0F);
        rFootClaw02.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        rFootClaw03 = new ModelPart(this);
        rFootClaw03.setPivot(-1.3F, 0.5F, -2.6F);
        rFoot.addChild(rFootClaw03);
        setRotationAngle(rFootClaw03, 0.2269F, 0.3229F, 0.0F);
        rFootClaw03.setTextureOffset(1, 48).addCuboid(-0.5F, -0.5F, -1.7F, 1.0F, 2.0F, 3.0F, 0.0F, true);

        neck = new ModelPart(this);
        neck.setPivot(0.0F, -18.2F, -1.0F);
        setRotationAngle(neck, -0.2531F, 0.0F, 0.0F);
        neck.setTextureOffset(0, 0).addCuboid(-3.5F, -1.5F, -1.0F, 7.0F, 5.0F, 5.0F, 0.0F, false);

        head2 = new ModelPart(this);
        head2.setPivot(0.0F, -0.5F, -3.6F);
        neck.addChild(head2);
        setRotationAngle(head2, 1.9199F, 0.0F, 0.0F);
        head2.setTextureOffset(44, 0).addCuboid(-4.0F, -3.0F, -4.4F, 8.0F, 6.0F, 7.0F, 0.0F, false);

        jawUpperLeft = new ModelPart(this);
        jawUpperLeft.setPivot(1.2F, -5.1F, -1.5F);
        head2.addChild(jawUpperLeft);
        setRotationAngle(jawUpperLeft, 0.0F, 0.0F, -0.1396F);
        jawUpperLeft.setTextureOffset(20, 36).addCuboid(-1.1F, -1.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        jawUpperLeft.setTextureOffset(69, 42).addCuboid(-0.11F, -0.7F, -4.1F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        upperTeethLeft01 = new ModelPart(this);
        upperTeethLeft01.setPivot(0.4F, -2.8F, -1.0F);
        jawUpperLeft.addChild(upperTeethLeft01);
        upperTeethLeft01.setTextureOffset(56, 37).addCuboid(-0.6F, 2.0F, -1.8F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        upperTeethLeft02 = new ModelPart(this);
        upperTeethLeft02.setPivot(0.0F, -3.2F, -1.0F);
        jawUpperLeft.addChild(upperTeethLeft02);
        setRotationAngle(upperTeethLeft02, 0.0F, 0.0F, 0.1367F);
        upperTeethLeft02.setTextureOffset(63, 38).addCuboid(-2.27F, 2.4F, -1.4F, 3.0F, 0.0F, 1.0F, 0.0F, false);

        jawUpperRight = new ModelPart(this);
        jawUpperRight.setPivot(-1.2F, -5.1F, -1.5F);
        head2.addChild(jawUpperRight);
        setRotationAngle(jawUpperRight, 0.0F, 0.0F, 0.1396F);
        jawUpperRight.setTextureOffset(20, 36).addCuboid(-0.9F, -1.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        jawUpperRight.setTextureOffset(69, 42).addCuboid(-0.81F, -0.7F, -4.1F, 1.0F, 2.0F, 4.0F, 0.0F, true);

        upperTeethRight = new ModelPart(this);
        upperTeethRight.setPivot(-0.4F, -2.8F, -1.0F);
        jawUpperRight.addChild(upperTeethRight);
        upperTeethRight.setTextureOffset(56, 37).addCuboid(-0.4F, 2.0F, -1.8F, 1.0F, 4.0F, 2.0F, 0.0F, true);

        jawLower = new ModelPart(this);
        jawLower.setPivot(0.0F, -2.1F, -3.0F);
        head2.addChild(jawLower);
        jawLower.setTextureOffset(39, 37).addCuboid(-2.0F, -3.9F, -1.5F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        lowerTeeth01 = new ModelPart(this);
        lowerTeeth01.setPivot(0.0F, -5.7F, 0.1F);
        jawLower.addChild(lowerTeeth01);
        lowerTeeth01.setTextureOffset(63, 41).addCuboid(-1.6F, 2.3F, -0.6F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        lowerTeeth02 = new ModelPart(this);
        lowerTeeth02.setPivot(0.0F, 0.0F, 0.0F);
        lowerTeeth01.addChild(lowerTeeth02);
        lowerTeeth02.setTextureOffset(63, 41).addCuboid(0.6F, 2.3F, -0.7F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        lEar01 = new ModelPart(this);
        lEar01.setPivot(2.8F, -0.1F, 2.8F);
        head2.addChild(lEar01);
        setRotationAngle(lEar01, 0.6981F, 0.1222F, -0.6981F);
        lEar01.setTextureOffset(77, 0).addCuboid(0.0F, -2.0F, -2.6F, 1.0F, 5.0F, 4.0F, 0.0F, true);

        lEar02 = new ModelPart(this);
        lEar02.setPivot(-0.3F, -3.0F, -0.5F);
        lEar01.addChild(lEar02);
        setRotationAngle(lEar02, 0.2269F, 0.0F, -0.2618F);
        lEar02.setTextureOffset(81, 11).addCuboid(-1.4F, 2.0F, -2.4F, 1.0F, 7.0F, 2.0F, 0.0F, true);

        rEar01 = new ModelPart(this);
        rEar01.setPivot(-2.8F, -0.1F, 2.8F);
        head2.addChild(rEar01);
        setRotationAngle(rEar01, 0.6981F, -0.1222F, 0.6981F);
        rEar01.setTextureOffset(77, 0).addCuboid(-1.0F, -2.0F, -2.6F, 1.0F, 5.0F, 4.0F, 0.0F, false);

        rEar02 = new ModelPart(this);
        rEar02.setPivot(0.3F, -3.0F, -0.5F);
        rEar01.addChild(rEar02);
        setRotationAngle(rEar02, 0.2269F, 0.0F, 0.2618F);
        rEar02.setTextureOffset(81, 11).addCuboid(0.4F, 2.0F, -2.4F, 1.0F, 7.0F, 2.0F, 0.0F, false);

        lCheekFur = new ModelPart(this);
        lCheekFur.setPivot(3.5F, -0.5F, -0.6F);
        head2.addChild(lCheekFur);
        setRotationAngle(lCheekFur, 0.1222F, -0.0873F, -0.5236F);
        lCheekFur.setTextureOffset(26, 4).addCuboid(0.0F, -0.8F, -3.3F, 0.0F, 6.0F, 5.0F, 0.0F, true);

        rCheekFur = new ModelPart(this);
        rCheekFur.setPivot(-3.5F, -0.5F, -0.6F);
        head2.addChild(rCheekFur);
        setRotationAngle(rCheekFur, 0.1222F, 0.0873F, 0.5236F);
        rCheekFur.setTextureOffset(26, 4).addCuboid(0.0F, -0.8F, -3.3F, 0.0F, 6.0F, 5.0F, 0.0F, false);

        snout = new ModelPart(this);
        snout.setPivot(0.0F, -4.2F, -0.1F);
        head2.addChild(snout);
        setRotationAngle(snout, 0.182F, 0.0F, 0.0F);
        snout.setTextureOffset(29, 35).addCuboid(-1.5F, -2.2F, -2.0F, 3.0F, 4.0F, 2.0F, 0.0F, false);

        fur01 = new ModelPart(this);
        fur01.setPivot(0.0F, 0.0F, -2.9F);
        neck.addChild(fur01);
        setRotationAngle(fur01, 1.9635F, 0.0F, 0.0F);
        fur01.setTextureOffset(90, 0).addCuboid(-3.5F, 2.0F, 0.0F, 7.0F, 7.0F, 2.0F, 0.1F, false);

        lArm01 = new ModelPart(this);
        lArm01.setPivot(5.5F, -15.0F, 2.0F);
        setRotationAngle(lArm01, 0.1745F, -0.0873F, -0.2356F);
        lArm01.setTextureOffset(32, 47).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        lArm02 = new ModelPart(this);
        lArm02.setPivot(0.0F, 11.5F, 0.0F);
        lArm01.addChild(lArm02);
        setRotationAngle(lArm02, -0.5236F, 0.0F, 0.1484F);
        lArm02.setTextureOffset(49, 46).addCuboid(-1.5F, -1.0F, -1.5F, 3.0F, 13.0F, 3.0F, 0.0F, false);

        lArmFur = new ModelPart(this);
        lArmFur.setPivot(0.4F, -4.0F, 0.0F);
        lArm02.addChild(lArmFur);
        setRotationAngle(lArmFur, -0.4363F, 0.0873F, 0.0873F);
        lArmFur.setTextureOffset(62, 50).addCuboid(-0.5F, -0.5F, 1.5F, 1.0F, 8.0F, 4.0F, 0.0F, false);

        lClawJoint = new ModelPart(this);
        lClawJoint.setPivot(0.0F, 8.6F, 0.0F);
        lArm02.addChild(lClawJoint);
        lClawJoint.setTextureOffset(0, 0).addCuboid(-0.4F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        lClaw01 = new ModelPart(this);
        lClaw01.setPivot(1.0F, 0.2F, 0.0F);
        lClawJoint.addChild(lClaw01);
        setRotationAngle(lClaw01, -0.1047F, 0.0F, 0.2269F);
        lClaw01.setTextureOffset(27, 0).addCuboid(-1.4F, 1.2F, -1.6F, 2.0F, 5.0F, 1.0F, 0.0F, false);

        lClaw02 = new ModelPart(this);
        lClaw02.setPivot(1.0F, 0.2F, -0.1F);
        lClawJoint.addChild(lClaw02);
        setRotationAngle(lClaw02, 0.0F, 0.0F, 0.2269F);
        lClaw02.setTextureOffset(27, 0).addCuboid(-1.4F, 1.2F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

        lClaw03 = new ModelPart(this);
        lClaw03.setPivot(1.0F, 0.2F, 0.8F);
        lClawJoint.addChild(lClaw03);
        setRotationAngle(lClaw03, 0.1047F, 0.0F, 0.2269F);
        lClaw03.setTextureOffset(27, 0).addCuboid(-1.4F, 1.2F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, false);

        leftHandItem = new ModelPart(this);
        leftHandItem.setPivot(-1.0F, 8.5F, -0.5F);
        lArm02.addChild(leftHandItem);


        rArm01 = new ModelPart(this);
        rArm01.setPivot(-5.5F, -15.0F, 2.0F);
        setRotationAngle(rArm01, 0.1745F, 0.0873F, 0.2356F);
        rArm01.setTextureOffset(32, 47).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        rArm02 = new ModelPart(this);
        rArm02.setPivot(0.0F, 11.5F, 0.0F);
        rArm01.addChild(rArm02);
        setRotationAngle(rArm02, -0.5236F, 0.0F, -0.1484F);
        rArm02.setTextureOffset(49, 46).addCuboid(-1.5F, -1.0F, -1.5F, 3.0F, 13.0F, 3.0F, 0.0F, true);

        rArmFur = new ModelPart(this);
        rArmFur.setPivot(-0.4F, -4.0F, 0.0F);
        rArm02.addChild(rArmFur);
        setRotationAngle(rArmFur, -0.4363F, -0.0873F, -0.0873F);
        rArmFur.setTextureOffset(62, 50).addCuboid(-0.5F, -0.5F, 1.5F, 1.0F, 8.0F, 4.0F, 0.0F, true);

        rClawJoint = new ModelPart(this);
        rClawJoint.setPivot(0.0F, 8.6F, 0.0F);
        rArm02.addChild(rClawJoint);
        rClawJoint.setTextureOffset(0, 0).addCuboid(-0.6F, 1.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        rClaw01 = new ModelPart(this);
        rClaw01.setPivot(-1.0F, 0.2F, 0.0F);
        rClawJoint.addChild(rClaw01);
        setRotationAngle(rClaw01, -0.1047F, 0.0F, -0.2269F);
        rClaw01.setTextureOffset(27, 0).addCuboid(-0.6F, 1.2F, -1.6F, 2.0F, 5.0F, 1.0F, 0.0F, true);

        rClaw02 = new ModelPart(this);
        rClaw02.setPivot(-1.0F, 0.2F, -0.1F);
        rClawJoint.addChild(rClaw02);
        setRotationAngle(rClaw02, 0.0F, 0.0F, -0.2269F);
        rClaw02.setTextureOffset(27, 0).addCuboid(-0.6F, 1.2F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, true);

        rClaw03 = new ModelPart(this);
        rClaw03.setPivot(-1.0F, 0.2F, 0.8F);
        rClawJoint.addChild(rClaw03);
        setRotationAngle(rClaw03, 0.1047F, 0.0F, -0.2269F);
        rClaw03.setTextureOffset(27, 0).addCuboid(-0.6F, 1.2F, -0.5F, 2.0F, 5.0F, 1.0F, 0.0F, true);

        rightHandItem = new ModelPart(this);
        rightHandItem.setPivot(1.0F, 8.5F, -0.5F);
        rArm02.addChild(rightHandItem);

    }

    @Override
    public void setAngles(WerepyreEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
        lArm01.render(matrixStack, buffer, packedLight, packedOverlay);
        rArm01.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}