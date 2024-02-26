using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class PedidoController : Controller
    {

        public readonly IPedidocs _pedidocs;

        PedidoController(IPedidocs pedidocs)
        {
            _pedidocs = pedidocs;   
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_pedidocs.GetPedidos());

        }

        public IActionResult Get(int id)
        {
            var obj = _pedidocs.ObtenerPorid(id);
            if (obj == null)
            {
                var error = NotFound("El producto(" + id.ToString() + ") no existe");
                return error;
            }
            else
            {
                return Ok(obj);
            }
        }
        [HttpPost("agregar")]
        public IActionResult add(TaPedido obj)
        {
            _pedidocs.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _pedidocs.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaPedido obj)
        {
            _pedidocs.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }

    }
}
