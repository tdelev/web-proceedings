 <%@page import="org.ictact.webproceedings.model.Author"%>
<%@page import="java.util.List"%>
<%@page import="org.ictact.webproceedings.model.PaperAuthor"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <meta name="citation_publisher" content="ICT-ACT"/>
  	<meta name="citation_title" content="${paper.title}"/>
  	<meta name="citation_firstpage" content="${paper.pageFrom}"/>
  	<meta name="citation_lastpage" content="${paper.pageTo}"/>
  	<meta name="citation_doi" content="${paper.doi}"/>
  	<meta name="citation_language" content="en"/>
  	<meta name="citation_abstract_html_url" content="http://${pageContext.request.serverName}${pageContext.request.contextPath}/${paper.conference.year}/paper/${paper.id}/${paper.titleSlug}">
  	<meta name="citation_pdf_url" content="http://${pageContext.request.serverName}${pageContext.request.contextPath}/attachment/paper/${paper.id}/${paper.pdf}"/>
  	
	<% 
		List<PaperAuthor> authors = (List<PaperAuthor>)request.getAttribute("authors");
		for(PaperAuthor pa : authors) {
			Author author = pa.getAuthor();
			pageContext.setAttribute("author", author);
	%>
  	<meta name="citation_author" content="${author.firstName} ${author.lastName}"/>
  	<meta name="citation_author_institution" content="${author.affiliation}"/>
  	<meta name="citation_author_email" content="${author.email}"/>
  	<%
		}
	%>
  	<meta name="citation_inbook_title" content="${paper.conference.title} Web Proceedings"/>
  	<meta name="citation_isbn" content="${paper.conference.isbn}"/>
  	<meta name="citation_publication_date" content="${paper.conference.date}"/>
    
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.png">

    <title>ICT Innovations Web Proceedings - ${paper.title}</title>

    <!-- Bootstrap core CSS -->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300italic,400italic,700italic,400,300,700&subset=latin,cyrillic-ext' rel='stylesheet' type='text/css'>
    
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/font-awesome.min.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 
 </head>
 
  