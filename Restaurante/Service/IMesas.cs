using Restaurante.Models;

namespace Restaurante.Service
{
    public interface InterfazRestauranteMesas
    {
         IEnumerable<TaMesa> GetMesas();
       
    }
}
