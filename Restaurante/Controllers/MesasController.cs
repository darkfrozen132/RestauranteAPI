using Microsoft.AspNetCore.Mvc;

using Restaurante.Service;
using Restaurante.Models;


namespace Restaurante.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class MesasController : ControllerBase
    {
        public readonly InterfazRestauranteMesas _mesa;

        public MesasController(InterfazRestauranteMesas mesa)
        {
            _mesa = mesa;
        }
        [HttpGet]
        public IActionResult Index()
        {
             return Ok(_mesa.GetMesas());
        }



    }
}
