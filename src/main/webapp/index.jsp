<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <section id="home">
            <h1>SalesForecaster</h1>
        </section>

        <div id="tree-container"></div>
    </tiles:putAttribute>
</tiles:insertDefinition>