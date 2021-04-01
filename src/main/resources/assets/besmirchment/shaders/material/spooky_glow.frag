#include frex:shaders/api/fragment.glsl
#include frex:shaders/lib/math.glsl

void frx_startFragment(inout frx_FragmentData fragData) {
    float e = frx_luminance(fragData.spriteColor.rgb) + 0.1;
    bool lit = e >  0.8 || (fragData.spriteColor.g - fragData.spriteColor.b) > 0.1f;
    fragData.emissivity = lit ? e: 0.0;
    fragData.diffuse = fragData.diffuse && !lit;
    fragData.ao = fragData.ao && !lit;
}