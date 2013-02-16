sideGroup = 0

loadIncremental = (start) ->
  $.blockUI
  $.get "/gallery/incremental/#{start}", (data) ->
    $('#images').append $(data)
  sideGroup++
  $.unblockUI

$ ->
  # Start pulling cards at the first index.
  # We are 0-based, because we pass this to a Scala List.
  loadIncremental(sideGroup)

  $(window).debounce(
    'scroll',
    (->
      if $(window).scrollTop() + $(window).height() > $(document).height() - 100
        loadIncremental(sideGroup)),
    200)