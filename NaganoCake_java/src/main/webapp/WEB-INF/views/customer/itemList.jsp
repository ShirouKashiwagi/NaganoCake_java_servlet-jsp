<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<!DOCTYPE html>
		<html>

			<head>
				<meta charset="UTF-8">
				<title>商品一覧 - Nagano Cake</title>
			</head>

			<body>

				<%-- 共通レイアウト --%>
					<%@ include file="/WEB-INF/views/common/application.jsp" %>

						<!--カスタマー側の商品一覧ページ-->
						<p id="notice"></p>
						<div class="container mt-5 pl-0">
							<div class="row">
								<div class="col-3 ml-3 mr-5">
									<!--ジャンル検索-->
									<table class="table">
										<thead>
											<th>
												ジャンル検索
											</th>
										</thead>
										<tbody>
											<tr>
												<!--	   <a href="${pageContext.request.contextPath}/items" class="${empty param.genre ? 'active' : ''}">すべて</a>	-->
												<!--        <c:forEach var="genre" items="${genres}">	-->
											<tr>
												<td>
													<!--		   <a href="${pageContext.request.contextPath}/items?genre=${genre.id}" class="${param.genre == genre.id ? 'active' : ''}">${genre.name}</a>	-->
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>
								<div class="col-9 ml-5">
									<div class="row">
										<h2 class="px-3"><strong>商品一覧</strong></h2>
									</div>

									<div class="row mt-3 mb-3 text-center text-xs-center text-sm-center text-md-left">

										<c:choose>
											<c:when test="${empty items}">
												<p>表示する商品がありません。</p>
											</c:when>
											<c:otherwise>
												<c:forEach var="item" items="${items}">
													<div class="col-xs-10 col-sm-10 col-md-6 col-lg-3">
														<div class="card w-100 bg-transparent border-0" style="width: 18rem;">

															<c:choose>
																<c:when test="${not empty item.imagePath}">
																	<p class="img-wrap">
																		<img src="${pageContext.request.contextPath}/images/items/${item.imagePath}"
																			alt="${item.name}" style="max-width: 200px; max-height: 200px;">
																	</p>
																</c:when>
																<c:otherwise>
																	<p class="img-wrap">
																		<img src="${pageContext.request.contextPath}/images/items/no-image.png"
																			style="max-width: 200px; max-height: 200px;">
																	</p>
																</c:otherwise>
															</c:choose>

															<div class="card-body">
																<p class="item-details item-name card-text">
																	${item.name}
																</p>

																<c:choose>
																	<c:when test="${item.isActive}">
																		<!--.to_s(:delimited)は3桁ごとにカンマ区切りで表示してくれる便利なメソッドです-->
																		<p class="item-details card-text">${item.price}円</p>
																	</c:when>
																	<c:otherwise>
																		<p class="text-danger font-weight-bold card-text">SOLD OUT!!</p>
																	</c:otherwise>
																</c:choose>
										</c:choose>
									</div>
								</div>
							</div>
							</c:forEach>
							</c:otherwise>
						</div>
						</div>
						</div>
						</div>
			</body>

		</html>