using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class PedidoDController : Controller
    {
        public readonly IPedidoD _pedidocsD;
        public PedidoDController(IPedidoD pedidocsD)
        {
            _pedidocsD = pedidocsD;
        }
        [HttpGet]
        public IActionResult Index()
        {
            return Ok(_pedidocsD.GetPedidoD());

        }

        public IActionResult Get(int id)
        {
            var obj = _pedidocsD.ObtenerPorid(id);
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
        public IActionResult add(TaPedidoDetalle obj)
        {
            _pedidocsD.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _pedidocsD.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaPedidoDetalle obj)
        {
            _pedidocsD.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }

    }
}

