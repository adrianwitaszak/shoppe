plugins {
    id("shoppe-component-mvi")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(Module.Data.sdk))
                implementation(project(Module.Data.repository))

                implementation(project(Module.Feature.shopPreview))
                implementation(project(Module.Feature.shops))
                implementation(project(Module.Feature.upcomingOrders))

                implementation(Libs.ArkIvanov.MVIKotlin.main)
                implementation(Libs.ArkIvanov.Essenty.parcelable)
            }
        }
    }
}