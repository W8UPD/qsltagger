$ ->
  $('#usermenu_link').click (event) =>
    event.stopPropagation()
    $('#usermenu').toggle()
  $('html').click (event) =>
    $('#usermenu').hide()

  selection = $('<div>').addClass('tagbox')
  container = $('#qslcard')

  container.mousedown (event) =>
    done = false
    click_y = event.pageY
    click_x = event.pageX
    selection.css(
      {
        'top': click_y,
        'left': click_x,
        'width': 0,
        'height': 0
      })
    selection.appendTo(container)

    container.mousemove (event) =>
      move_x = event.pageX
      move_y = event.pageY
      width  = Math.abs(move_x - click_x)
      height = Math.abs(move_y - click_y)

      selection.css(
        {
          'width': width,
          'height': height
        })
      if move_x < click_x
        selection.css(
          {
            'left': click_x - width
          })

      if move_y < click_y
        selection.css(
          {
            'top': click_y - height
          })

    container.mouseup (event) =>
      move_x = event.pageX
      move_y = event.pageY
      width  = Math.abs(move_x - click_x)
      height = Math.abs(move_y - click_y)



      container.off('mousemove')
      container.off('mouseup')
