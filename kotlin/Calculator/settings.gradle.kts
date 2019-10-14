rootProject.name = "Calculator"

if (File("composite-enable").exists() && File("../MathUtil").exists()) {
    includeBuild("../MathUtil") {
        dependencySubstitution {
            substitute(module("com.example.math:MathUtil")).with(project(":"))
        }
    }
}