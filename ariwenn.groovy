job('Ariwenn-ToDoList-Via-DSL') {
    description("Ariwenn's ToDo list for  ${new Date()}, the project is a small Maven project hosted on github")
    scm {
        git("https://github.com/SchegolevaNV/ToDoList.git", 'master')
    }
    triggers {
        scm('* * * * *')
    }
    steps {
        maven('clean package', 'pom.xml')
    }
    publishers {
        //archive the war file generated
        archiveArtifacts '**/*.jar'
    }
}
