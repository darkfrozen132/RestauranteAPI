using Microsoft.AspNetCore.Mvc;
using Restaurante.Models;
using Restaurante.Service;

namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]

    public class ClienteController : Controller
    {
        public readonly ICliente _cliente;

        ClienteController (ICliente cliente)
        {
            _cliente = cliente;
        }
       [HttpGet]
        public IActionResult Index()
        {
            return Ok(_cliente.GetClientes());
        }
        public IActionResult Get(int id)
        {
            var obj = _cliente.ObtenerPorid(id);
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
        public IActionResult add(TaCliente obj)
        {
            _cliente.add(obj);
            return CreatedAtAction(nameof(add), obj);
        }
        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _cliente.eleminar(id);
            return NoContent();
        }
        [HttpPut("modificar")]
        public IActionResult put(TaCliente obj)
        {
            _cliente.modificar(obj);
            return CreatedAtAction(nameof(put), obj);
        }





    }
}
