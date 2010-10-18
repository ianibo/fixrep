<html>
    <head>
        <title>FixRep Aggregated Extraction Service - </title>
        <meta name="layout" content="main" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="pageBody">
            <h1>Welcome to the Fixrep Aggregated Extraction Service</h1>
            <p>This web service is an experimental addon which extends the Spatial and Temporal extraction service produced for WP4 of the JISC Funded <a href="http://www.ukoln.ac.uk/projects/fixrep/">FixRep</a> project.
               That workpackage developed <a href="http://fixrep.k-int.com/fixrepws">this web service</a> which can be used to extract 
               Temporal and Spatial named entities from input xml documents. This service encapsulates that original service in an
               extensible pluggable framework that is intended to both assist in the evaluation of different metadata extraction services,
               and to provide a unified interface to such a set of services.</p><br/>
            <p>
               The basic service can be accessed by making a HTTP multipart post to http://localhost:8080/fixrep/analyse/extract.[html|xml|json] A simple html form is provided at 
               <a href="http://fixrep.k-int.com/fixrep/analyse">http://fixrep.k-int.com/fixrep/analyse</a> which will report back extracted terms in a html response.
            </p>

            <p>
              Comments / Queries / Suggestions can be emailed to <b>fixrep at k hypen int dot com</b>
            </p>

        </div>
    </body>
</html>
