package beSen.generatorProject.model;

/**
 * 对应 pom 文件中的项目信息
 * <groupId>${groupId}</groupId>
 * <artifactId>${artifactId}</artifactId>
 * <version>${version}</version>
 * <name>${name}</name>
 * <description>${description}</description>
 */
public class ProjectInfo {

    private String groupId;
    private String artifactId;
    private String version;
    private String name;
    private String description;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectInfo(String groupId, String artifactId, String version, String name, String description) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    public ProjectInfo() {
    }
}
