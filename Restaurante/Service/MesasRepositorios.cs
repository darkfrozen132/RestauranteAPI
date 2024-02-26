using Restaurante.Models;

namespace Restaurante.Service
{
    public class MesasRepositorios : InterfazRestauranteMesas

    {
        private Conexion conex= new Conexion();

        public IEnumerable<TaMesa> GetMesas()
        {
            return conex.TaMesas;


        }
    }
}
