$ ->
  $('#usermenu_link').click (event) =>
    event.stopPropagation()
    $('#usermenu').toggle()
  $('html').click (event) =>
    $('#usermenu').hide()
  $(window).blur =>
    $('#usermenu').hide()