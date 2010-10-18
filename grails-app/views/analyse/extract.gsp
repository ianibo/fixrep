<html>
<head>
<meta name='layout' content='main' />
<title>Fixrep Term Extraction</title>
</head>

<body>

<g:form controller="analyse" method="post" action="extract" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</g:form>

<ul>
<g:each in="${metadata}" var="pluginResult">
   <li>
     <ul>
       <b>${pluginResult.code}: ${pluginResult.status} ${pluginResult.message} in ${pluginResult.elapsed} ms</b><br/>
       <g:each in="${pluginResult.terms}" var="termInfo">
         <li>
           ${termInfo}
         </li>
       </g:each>
     </ul>
   </li>
</g:each>
</ul>

</body>

</html>
